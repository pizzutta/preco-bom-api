package com.pizzutti.precobom.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "A data transfer object that contains information to register a grocery list")
public record GroceryListRegisterDTO(
        @NotBlank
        @Schema(description = "The grocery list's name", example = "Weekly Essentials")
        String name,
        @NotNull
        @Schema(description = "The user ID", example = "1")
        Long userId
) {
}
