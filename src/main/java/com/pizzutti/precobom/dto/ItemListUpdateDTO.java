package com.pizzutti.precobom.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemListUpdateDTO(
        @NotNull
        @Schema(description = "The item list ID", example = "1")
        Long id,
        @NotNull
        @Positive
        @Schema(description = "The quantity defined for that product", example = "5")
        Integer quantity,
        @NotNull
        @Schema(description = "If true, the item has already been checked on the grocery list")
        Boolean checked
) {
}
