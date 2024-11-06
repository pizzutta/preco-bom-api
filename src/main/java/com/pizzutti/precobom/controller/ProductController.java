package com.pizzutti.precobom.controller;

import com.pizzutti.precobom.dto.IdDTO;
import com.pizzutti.precobom.dto.ProductRegisterDTO;
import com.pizzutti.precobom.dto.ProductUpdateDTO;
import com.pizzutti.precobom.model.Product;
import com.pizzutti.precobom.service.MarketService;
import com.pizzutti.precobom.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@Tag(name = "Product")
public class ProductController {

    @Autowired
    private ProductService service;
    @Autowired
    private MarketService marketService;

    @GetMapping
    @Operation(description = "Gets all the products registered in the app")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(
                            schema = @Schema(implementation = Product.class)))),
            @ApiResponse(responseCode = "204", description = "No products were found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content)
    })
    //TODO: implement filtering and sorting on getAll endpoints
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = service.findAll();
        return (products.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    @Operation(description = "Gets a product by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
    })
    public ResponseEntity<Product> getById(@Parameter(description = "The product ID", example = "1") @PathVariable(value =
            "id") Long id) {
        Optional<Product> product = service.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/market/{marketId}")
    @Operation(description = "Gets all the market products by the market ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(
                            schema = @Schema(implementation = Product.class)))),
            @ApiResponse(responseCode = "204", description = "No products were found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content)
    })
    public ResponseEntity<List<Product>> getByMarketId(@Parameter(description = "The market ID", example = "1") @PathVariable(value =
            "marketId") Long marketId) {
        List<Product> products = service.findByMarketId(marketId);
        return (products.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(products);
    }

    @PostMapping
    @Operation(description = "Creates a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "400", description = "Request content is invalid", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content)
    })
    public ResponseEntity<Product> save(@RequestBody @Valid ProductRegisterDTO data) {
        Product product = new Product();
        product.setName(data.name());
        product.setPrice(data.price());
        product.setMeasuringUnit(data.measuringUnit());
        product.setImage(data.image());
        product.setMarket(marketService.findById(data.marketId()).get());

        service.save(product);

        URI uri = URI.create("/product/" + product.getId());
        return ResponseEntity.created(uri).body(product);
    }

    @PutMapping
    @Operation(description = "Updates an existing product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "400", description = "Request content is invalid", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
    })
    public ResponseEntity<Product> update(@RequestBody @Valid ProductUpdateDTO data) {
        Product product = service.findById(data.id()).orElseThrow(EntityNotFoundException::new);
        product.setName(data.name());
        product.setPrice(data.price());
        product.setMeasuringUnit(data.measuringUnit());
        product.setImage(data.image());

        service.save(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping
    @Operation(description = "Deletes an existing product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted", content = @Content),
            @ApiResponse(responseCode = "400", description = "Request content is invalid", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content)
    })
    public ResponseEntity<Void> deleteById(@RequestBody @Valid IdDTO data) {
        service.deleteById(data.id());
        return ResponseEntity.noContent().build();
    }
}
