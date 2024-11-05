package com.pizzutti.precobom.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "A data transfer object that contains information to update a product")
public record ProductUpdateDTO(
        @NotNull
        @Schema(description = "The product ID", example = "1")
        Long id,
        @NotBlank
        @Schema(description = "The product's name", example = "Grape Tomatoes")
        String name,
        @NotNull
        @Schema(description = "The product's price determined by the market", example = "2.25")
        Double price,
        @NotBlank
        @Schema(description = "The unit used to measure the product", example = "lbs")
        String measuringUnit,
        @NotBlank
        @Schema(description = "An URL or a path to an image provided by the market to represent the product",
                example = "https://mysite.com/images/my-product.jpg")
        String image
) {
}
