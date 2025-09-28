package com.cuenta.listeners;

import com.cuenta.DTOs.MessageDTO;
import com.cuenta.entity.CuentaEntity;
import com.cuenta.events.NotificationSender;
import com.cuenta.repository.CuentaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageListener {
    private final CuentaRepository cuentaRepository;
    private final RabbitTemplate rabbitTemplate;
    private final Jackson2JsonMessageConverter messageConverter;
    private final NotificationSender notificationSender;

    @Value("${queue.exchange}")
    private String exchange;

    @Value("${queue.routing-key}")
    private String cajeroRoutingKey;

    @Value("${queue-notification.routing-key}")
    private String notificationRoutingKey;

    @RabbitListener(queues = "${queue.name}")
    public void receiveMessage(MessageDTO message) {
        CuentaEntity cuentaEntity = cuentaRepository.findById(Long.parseLong(message.cuenta())).orElseThrow();
        if (cuentaEntity.getMonto() >= message.payload().monto()) {
            notificationSender.sendMessage("Retiro confirmado");
            rabbitTemplate.convertAndSend(exchange, cajeroRoutingKey, message);
//            rabbitTemplate.convertAndSend(exchange, notificationRoutingKey, "");
            log.info("Puede retirar dinero");
        } else {
            rabbitTemplate.convertAndSend(exchange, cajeroRoutingKey, "Dinero insuficiente");
            log.error("Dinero insuficiente");
        }
        log.info("Monto a retirar es {}", message.payload().monto());
        log.info("Monto que tiene en la cuenta {}", cuentaEntity.getMonto());

    }

}
