package com.pizzutti.precobom.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MarketUpdateDTO(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String address,
        @NotBlank
        String image
) {
}
