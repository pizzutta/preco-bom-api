package com.pizzutti.precobom.dto;

import com.pizzutti.precobom.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @Email
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotNull
        UserRole role
) {
}
