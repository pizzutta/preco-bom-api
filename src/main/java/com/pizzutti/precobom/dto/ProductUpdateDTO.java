package com.pizzutti.precobom.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductUpdateDTO(
        @NotNull
        Long id,
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
