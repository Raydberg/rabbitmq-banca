package com.cajero.DTOs;

public record MessageDTO(
        String cuenta,
        MessageRequestDTO payload
) {
}
