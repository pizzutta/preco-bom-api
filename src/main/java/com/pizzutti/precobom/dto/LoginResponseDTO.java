package com.pizzutti.precobom.dto;

public record LoginResponseDTO(
        Long id,
        String email,
        String role,
        String token
) {
}
