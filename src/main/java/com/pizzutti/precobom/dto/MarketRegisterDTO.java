package com.pizzutti.precobom.dto;

import jakarta.validation.constraints.NotBlank;

public record MarketRegisterDTO(
        @NotBlank
        String name,
        @NotBlank
        String address,
        @NotBlank
        String image
) {
}
