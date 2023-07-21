package com.pizzutti.precobom.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRegisterDTO(
        @NotBlank
        String name,
        @NotNull
        Double price,
        @NotBlank
        String measuringUnit,
        @NotBlank
        String image,
        @NotNull
        Long marketId
) {
}
