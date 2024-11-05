package com.pizzutti.precobom.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "A data transfer object that contains information to register a market")
public record MarketRegisterDTO(
        @NotBlank
        @Schema(description = "The market's name", example = "QuickCart")
        String name,
        @NotBlank
        @Schema(description = "The market's address", example = "123 Oak Ave, Everdale, ZIP 54321")
        String address,
        @NotBlank
        @Schema(description = "An URL or a path to an image provided by the market to represent it",
                example = "https://mysite.com/images/my-logo.jpg")
        String image
) {
}
