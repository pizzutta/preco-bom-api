package com.pizzutti.precobom.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GroceryListRegisterDTO(
        @NotBlank
        String name,
        @NotNull
        Long userId
) {
}
