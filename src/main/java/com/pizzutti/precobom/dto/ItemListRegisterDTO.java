package com.pizzutti.precobom.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "A data transfer object that contains information to register an item list")
public record ItemListRegisterDTO(
        @NotNull
        @Schema(description = "The product ID", example = "1")
        Long productId,
        @NotNull
        @Positive
        @Schema(description = "The quantity defined for that product", example = "5")
        Integer quantity,
        @NotNull
        @Schema(description = "If true, the item has already been checked on the grocery list")
        Boolean checked,
        @NotNull
        @Schema(description = "The grocery list ID", example = "1")
        Long groceryListId
) {
}
