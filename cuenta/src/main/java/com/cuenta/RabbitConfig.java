package com.cuenta;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class RabbitConfig {

    @Value("${queue.name}")
    private String queueName;

    @Value("${queue.exchange}")
    private String exchangeName;

    @Value("${queue.routing-key}")
    private String routingKey;

    // NO se declara la cola (ya existe en RabbitMQ como quorum)

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding binding(TopicExchange exchange) {
        return new Binding(
                queueName,
                Binding.DestinationType.QUEUE,
                exchangeName,
                routingKey,
                null
        );
    }
}
