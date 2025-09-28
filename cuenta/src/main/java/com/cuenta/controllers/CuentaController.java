package com.cuenta.controllers;

import com.cuenta.entity.CuentaEntity;
import com.cuenta.events.NotificationSender;
import com.cuenta.repository.CuentaRepository;
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
    private final NotificationSender notificationSender;

    @PutMapping("/actualizar-saldo")
    public ResponseEntity<String> actualizarSaldo(@RequestParam String cuentaId, @RequestParam Double monto) {
        Long cuentaIdLong = Long.parseLong(cuentaId);
        CuentaEntity cuenta = cuentaRepository.findById(cuentaIdLong)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        cuenta.setMonto(cuenta.getMonto() - monto);
        cuentaRepository.save(cuenta);

        log.info("Saldo actualizado para la cuenta {}: nuevo saldo {}", cuentaIdLong, cuenta.getMonto());
        return ResponseEntity.ok("Saldo actualizado correctamente");
    }
    @PostMapping
    public ResponseEntity<String> enviarNotificacion(@RequestBody String message) {
        notificationSender.sendMessage(message);
        return ResponseEntity.ok("Notificacion enviada" + message);
    }

}
