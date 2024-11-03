package com.pizzutti.precobom.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemListUpdateDTO(
        @NotNull
        Long id,
        @NotNull
        @Positive
        Integer quantity,
        @NotNull
        Boolean checked
) {
}
