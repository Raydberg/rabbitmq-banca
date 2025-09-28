package com.cajero.events;


import com.cajero.DTOs.MessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;
    private final Jackson2JsonMessageConverter messageConverter;

    @Value("${queue.exchange}")
    private String exchange;

    @Value("${queue.routing-key}")
    private String routingKey;


    public void sendMessage(MessageDTO message) {
        try {
            //Utiliza el convertidor de Jackson
            rabbitTemplate.setMessageConverter(messageConverter);
            //Enviamos el mensaje ya convertido en JSON
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
            log.info("Mensaje enviado {}", message);

        } catch (
                RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
