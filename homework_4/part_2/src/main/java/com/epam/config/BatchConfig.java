package com.epam.config;


import com.epam.entity.User;
import com.epam.mapper.UserMapper;
import com.epam.service.EmailSender;
import com.epam.step.Processor;
import com.epam.step.Writer;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@EnableBatchProcessing
@Configuration
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
@ComponentScan("com.epam")
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    private final EmailSender emailSender;

    @Bean
    public DataSource connection() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/hw4p2?createDatabaseIfNotExist=true&characterEncoding=UTF-8&useTimezone=true&serverTimezone=UTC&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("12345Art");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }


    @Bean
    public JdbcCursorItemReader<User> itemReader() throws SQLException {
        JdbcCursorItemReader<User> reader = new JdbcCursorItemReader();
        reader.setDataSource(connection());
        reader.setSql("select * from user");
        reader.setRowMapper(new UserMapper());
        return reader;
    }

    @Bean
    public ItemProcessor itemProcessor() {
        return new Processor();
    }

    @Bean
    public ItemWriter itemWriter() {
        return new Writer(emailSender);
    }

    @Bean
    protected Step step1() throws SQLException {
        return stepBuilderFactory.get("step1").<User, User>chunk(1)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean(name = "userJob")
    public Job userJob() throws SQLException {
        return jobBuilderFactory.get("userJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }
}
