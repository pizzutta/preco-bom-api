package com.pizzutti.precobom.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "A data transfer object that contains information for an user login")
public record AuthenticationDTO(
        @Email
        @NotBlank
        @Schema(description = "The user's email", example = "my@email.com")
        String email,
        @NotBlank
        @Schema(description = "The user's password", example = "mypassword")
        String password
) {
}
