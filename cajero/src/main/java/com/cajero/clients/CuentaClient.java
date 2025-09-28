package com.cajero.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cuenta-service", url = "${cuenta.service.url}")
public interface CuentaClient {
    @PutMapping("/cuenta/actualizar-saldo")
    void actualizarSalgo(@RequestParam("cuentaId") String cuentaId, @RequestParam("monto") Double monto);
}
