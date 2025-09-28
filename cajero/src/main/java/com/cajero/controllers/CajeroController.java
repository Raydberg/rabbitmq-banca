package com.cajero.controllers;

import com.cajero.DTOs.MessageDTO;
import com.cajero.DTOs.MessageRequestDTO;
import com.cajero.events.MessageSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cajero")
@Slf4j
public class CajeroController {
    private final MessageSender messageSender;


    @PostMapping("{cuenta}")
    public ResponseEntity<String> retirarDinero(@RequestBody MessageRequestDTO payload, @PathVariable String cuenta) {
        try {
            MessageDTO messageDTO = new MessageDTO(cuenta, payload);
            messageSender.sendMessage(messageDTO);
            return ResponseEntity.ok("Enviar monto a retirar " + messageDTO);
        } catch (
                Exception e) {
            return ResponseEntity.status(500).body("Error al enviar el mensaje");
        }
    }


}
