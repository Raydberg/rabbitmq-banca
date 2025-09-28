package com.cuenta.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationSender {
    private final RabbitTemplate rabbitTemplate;
    private final Jackson2JsonMessageConverter messageConverter;

    @Value("${queue.exchange}")
    private String exchange;

    @Value("${queue-notification.routing-key}")
    private String routingKey;


    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        log.info("Mensaje enviado {}", message);
    }

}
