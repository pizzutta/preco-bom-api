package com.pizzutti.precobom.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "A data transfer object that contains information of a logged user")
public record LoginResponseDTO(
        @Schema(description = "The user ID", example = "1")
        Long id,
        @Schema(description = "The user's email", example = "my@email.com")
        String email,
        @Schema(description = "The user's role in the app", example = "ADMIN")
        String role,
        @Schema(description = "A generated token to be user in protected requests",
                example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                        ".eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ" +
                        ".SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
        String token
) {
}
