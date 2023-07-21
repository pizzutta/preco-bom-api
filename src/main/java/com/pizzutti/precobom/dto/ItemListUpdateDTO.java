package com.pizzutti.precobom.dto;

import jakarta.validation.constraints.NotNull;

public record ItemListUpdateDTO(
        @NotNull
        Long id,
        @NotNull
        ItemListRegisterDTO itemList
) {
}
