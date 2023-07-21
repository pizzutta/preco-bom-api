package com.pizzutti.precobom.dto;

import jakarta.validation.constraints.NotNull;

public record IdDTO(
        @NotNull
        Long id
) {
}
