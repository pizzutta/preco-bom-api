package com.pizzutti.precobom.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemListRegisterDTO(
        @NotNull
        Long productId,
        @NotNull
        @Positive
        Integer quantity,
        @NotNull
        Boolean checked,
        @NotNull
        Long groceryListId
) {
}
