package com.pizzutti.precobom.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "A data transfer object that contains information an ID number")
public record IdDTO(
        @NotNull
        @Schema(description = "The ID", example = "1")
        Long id
) {
}
