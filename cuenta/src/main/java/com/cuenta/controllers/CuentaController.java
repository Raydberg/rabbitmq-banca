package com.cuenta.controllers;

import com.cuenta.entity.CuentaEntity;
import com.cuenta.events.NotificationSender;
import com.cuenta.repository.CuentaRepository;
import com.cuenta.services.CuentaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cuenta")
@Slf4j
public class CuentaController {
    private final CuentaRepository cuentaRepository;
    private final CuentaService cuentaService;
    private final NotificationSender notificationSender;

    @PutMapping("/actualizar-saldo")
    public ResponseEntity<String> actualizarSaldo(@RequestParam String cuentaId, @RequestParam Double monto) {
        cuentaService.actualizarSaldo(cuentaId, monto);
        log.info("Saldo actualizado para la cuenta {}", cuentaId);
        return ResponseEntity.ok("Saldo actualizado correctamente");
    }

    @PostMapping
    public ResponseEntity<String> enviarNotificacion(@RequestBody String message) {
        notificationSender.sendMessage(message);
        return ResponseEntity.ok("Notificacion enviada" + message);
    }

}
