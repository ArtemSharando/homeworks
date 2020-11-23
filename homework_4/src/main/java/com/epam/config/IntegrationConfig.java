package com.epam.config;

import com.epam.entity.Order;
import com.epam.entity.OrderState;
import com.epam.service.Storage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.GenericSelector;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.MessageChannel;

import java.util.Iterator;
import java.util.List;


@Configuration
@EnableIntegration
@ComponentScan("com.epam")
@IntegrationComponentScan
public class IntegrationConfig {

    public static final String file = "src/main/resources/file.csv";

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(IntegrationConfig.class);
        ctx.refresh();

        DirectChannel out1 = ctx.getBean("outputChannel1", DirectChannel.class);
        out1.subscribe(x -> System.out.println(x.getPayload() + " outputChannel1"));

        DirectChannel out2 = ctx.getBean("outputChannel2", DirectChannel.class);
        out2.subscribe(x -> System.out.println(x.getPayload() + " outputChannel2"));


        List<Order> orders = ctx.getBean(IntegrationConfig.MGateway.class).parse(IntegrationConfig.file);

        System.out.println(orders + " orders");

        for (Order order : orders) {
            ctx.getBean(MGateway.class).addOrder(order);
        }

        System.out.println(Storage.storage + " global storage");

    }

    @Bean
    MessageChannel outputChannel1() {
        return new DirectChannel();
    }

    @Bean
    MessageChannel outputChannel2() {
        return new DirectChannel();
    }


    @MessagingGateway
    public interface MGateway {

        @Gateway(requestChannel = "outputChannel1")
        List<Order> parse(String file);

        @Gateway(requestChannel = "outputChannel2")
        void addOrder(Order order);
    }


    @Bean
    public IntegrationFlow parser() {
        return IntegrationFlows.from("outputChannel1")
                .handle("CSVOrderParser", "parse")
                .get();
    }

    @Bean
    public IntegrationFlow adder() {
        return IntegrationFlows.from("outputChannel2")
                .handle("storage", "addOrder")
                .get();
    }

    @Bean
    public GenericSelector<Order> notCanceledOrders() {
        return order -> !order.getOrderState().equals(OrderState.CANCELED);
    }

}
