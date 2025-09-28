package com.cajero;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageSender {
 private final RabbitTemplate rabbitTemplate;


    @Value("${queue.exchange}")
    private String exchange;

    @Value("${queue.routing-key}")
    private String routingKey;


    public void sendMessage(String message){
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        log.info("Mensaje enviado {}",message);
    }
}
