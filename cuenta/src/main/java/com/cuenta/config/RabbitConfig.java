package com.cuenta.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${queue.name}")
    private String cuentaQueueName;

    @Value("${queue.exchange}")
    private String exchangeName;

    @Value("${queue.routing-key}")
    private String cuentaRoutingKey;

    @Value("${queue-notification.name}")
    private String queueNotificacionName;
    @Value("${queue-notification.routing-key}")
    private String routingKeyNotificacion;


    // NO se declara la cola (ya existe en RabbitMQ como quorum)
    //Declaracion del intercambio
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    /**
     * Configuracion para el QUEUE de CUENTA
     *
     */

    @Bean
    public Binding cuentaBinding(TopicExchange exchange) {
        return new Binding(
                cuentaQueueName,
                Binding.DestinationType.QUEUE,
                exchangeName,
                cuentaRoutingKey,
                null
        );
    }


    /**
     * Configuracion para el queue de NOTIFICACIONES
     *
     */

    @Bean
    public Binding notificationBinding(TopicExchange exchange) {
        return new Binding(
                queueNotificacionName,
                Binding.DestinationType.QUEUE,
                exchangeName,
                routingKeyNotificacion,
                null
        );
    }


    // Configuracion para desearilizar automaticamente con JACKSON
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
