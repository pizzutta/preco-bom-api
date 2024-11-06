package com.pizzutti.precobom.dto;

import com.pizzutti.precobom.model.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "A data transfer object that contains information for an user registration")
public record RegisterDTO(
        @Email
        @NotBlank
        @Schema(description = "The user's email", example = "my@email.com")
        String email,
        @NotBlank
        @Schema(description = "The user's password", example = "mypassword")
        String password,
        @NotNull
        @Schema(description = "The user's role in the app", example = "ADMIN")
        UserRole role
) {
}
