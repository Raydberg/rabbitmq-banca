package com.cajero.DTOs;

public record MessageRequestDTO(
        Double monto,
        String sucursal
) {
}
