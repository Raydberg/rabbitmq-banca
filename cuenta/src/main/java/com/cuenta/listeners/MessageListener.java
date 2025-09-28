package com.cuenta.listeners;

import com.cuenta.DTOs.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageListener {

    @RabbitListener(queues = "${queue.name}")
    public void receiveMessage(MessageDTO message) {
        log.info("Mensaje recibido: {}", message);

    }

}
