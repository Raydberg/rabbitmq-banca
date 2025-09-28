package com.cajero.listener;

import com.cajero.DTOs.MessageDTO;
import com.cajero.clients.CuentaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageListener {

    private final CuentaClient cuentaClient;

    @RabbitListener(queues = "${queue.name}")
    public void receiveApproved(MessageDTO message) {
        log.info("Mensaje recibido {}", message);
        if (message.payload().monto() != null && message.cuenta() != null) {
            cuentaClient.actualizarSalgo(message.cuenta(), message.payload().monto());
            log.info("Saldo actualizado en el microservicio cuenta para la cuenta {}", message.cuenta());
        } else {
            log.error("El mensaje recibido no contiene datos válidos");
        }
    }
}
