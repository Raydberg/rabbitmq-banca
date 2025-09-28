package com.cuenta.controllers;

import com.cuenta.events.NotificationSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cuenta")
@Slf4j
public class CuentaController {

    private final NotificationSender notificationSender;

    @PostMapping
    public ResponseEntity<String> enviarNotificacion(@RequestBody String message) {
        notificationSender.sendMessage(message);
        return ResponseEntity.ok("Notificacion enviada" + message);
    }

}
