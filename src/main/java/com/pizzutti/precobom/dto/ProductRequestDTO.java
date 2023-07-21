package com.pizzutti.precobom.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Sort.Order;

public record ProductRequestDTO(
        @NotBlank
        Order order
) {
}
