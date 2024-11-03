package com.pizzutti.precobom.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GroceryListUpdateDTO(
        @NotNull
        Long id,
        @NotBlank
        String name
) {
}
