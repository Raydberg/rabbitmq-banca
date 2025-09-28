package com.notificacion.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationListener {
    @RabbitListener(queues = "${queue.name}")
    public void receiveMessage(String message) {
        log.info("Mensaje recibido {}", message);
    }
}
