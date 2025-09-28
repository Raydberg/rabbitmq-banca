package com.cajero;

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


    @PostMapping
    public ResponseEntity<String> retirarDinero(@RequestBody String message){
        messageSender.sendMessage(message);
        return ResponseEntity.ok("Enviar monto a retirar " + message);
    }





}
