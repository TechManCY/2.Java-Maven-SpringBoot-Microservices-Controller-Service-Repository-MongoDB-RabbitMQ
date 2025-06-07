package com.example.activityservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange; // Or DirectExchange, FanoutExchange depending on your needs
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.*;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Bean
    public Queue activityQueue(){
        return new Queue(queueName, true);
    }

    // Declare the exchange
    @Bean
    public Exchange activityExchange() {
        // You can choose between TopicExchange, DirectExchange, FanoutExchange
        // Based on your application.yml, 'routing.key' suggests a TopicExchange
        return new TopicExchange(exchangeName);
    }

    // Bind the queue to the exchange with the routing key
    @Bean
    public Binding binding(Queue activityQueue, TopicExchange activityExchange) {
        return BindingBuilder.bind(activityQueue)
                .to(activityExchange)
                .with(routingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter () {
        return new Jackson2JsonMessageConverter();
    }
}
