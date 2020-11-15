package ua.dnipro.epam.homework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@PropertySource("classpath:log4j.properties")
public class PropertiesConfig {
}
