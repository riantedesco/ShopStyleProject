package com.compass.msorder.configuration.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.compass.msorder.utils.constants.RabbitMQConstants.*;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queueAuditPublisher() {
        return new Queue(ORDER_TO_AUDIT_QUEUE_NAME, true);
    }

    @Bean
    public Queue queueCatalogPublisher() {
        return new Queue(ORDER_TO_CATALOG_QUEUE_NAME, true);
    }

    @Bean
    public Queue queuePaymentPublisher() {
        return new Queue(ORDER_TO_PAYMENT_QUEUE_NAME, true);
    }

    @Bean
    public Queue queuePaymentListener() {
        return new Queue(PAYMENT_TO_ORDER_QUEUE_NAME, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding bindingAuditPublisher() {
        return BindingBuilder.bind(queueAuditPublisher()).to(exchange()).with(ORDER_TO_AUDIT_ROUTINGKEY_NAME);
    }

    @Bean
    Binding bindingCatalogPublisher() {
        return BindingBuilder.bind(queueCatalogPublisher()).to(exchange()).with(ORDER_TO_CATALOG_ROUTINGKEY_NAME);
    }

    @Bean
    Binding bindingPaymentPublisher() {
        return BindingBuilder.bind(queuePaymentPublisher()).to(exchange()).with(ORDER_TO_PAYMENT_ROUTINGKEY_NAME);
    }

    @Bean
    Binding bindingPaymentListener() {
        return BindingBuilder.bind(queuePaymentListener()).to(exchange()).with(PAYMENT_TO_ORDER_ROUTINGKEY_NAME);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
