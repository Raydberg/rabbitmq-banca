package com.cajero.listener;

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
    public void receiveApproved(String message) {
        log.info("Mensaje recibido {}", message);
        if (message.equals("Dinero retirado")) {
            String cuentaId = "1";
            Double monto = 10.0;

            cuentaClient.actualizarSalgo(cuentaId, monto);
            log.info("Salgo actualizado en el microservicio cuenta");

        }
    }
}
