package com.pizzutti.precobom.controller;

import com.pizzutti.precobom.dto.IdDTO;
import com.pizzutti.precobom.dto.MarketRegisterDTO;
import com.pizzutti.precobom.dto.MarketUpdateDTO;
import com.pizzutti.precobom.model.Market;
import com.pizzutti.precobom.service.MarketService;
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
import java.util.UUID;

@RestController
@RequestMapping("/market")
@Tag(name = "Market")
public class MarketController {

    @Autowired
    private MarketService service;

    @GetMapping
    @Operation(description = "Gets all the markets registered in the app")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Markets found",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(
                            schema = @Schema(implementation = Market.class)))),
            @ApiResponse(responseCode = "204", description = "No markets were found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content)
    })
    public ResponseEntity<List<Market>> getAll() {
        List<Market> markets = service.findAll();
        return (markets.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(markets);
    }

    @GetMapping("/{id}")
    @Operation(description = "Gets a market by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Market found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Market.class))),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Market not found", content = @Content)
    })
    public ResponseEntity<Market> getById(@Parameter(description = "The market ID", example = "1")
                                          @PathVariable(value = "id") Long id) {
        Optional<Market> market = service.findById(id);
        return market.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/uuid/{uuid}")
    @Operation(description = "Gets a market by its UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Market found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Market.class))),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Market not found", content = @Content)
    })
    public ResponseEntity<Market> getByUUID(@Parameter(description = "The market UUID",
            example = "550e8400-e29b-41d4-a716-446655440000") @PathVariable(value = "uuid") UUID uuid) {
        Optional<Market> market = service.findByUUID(uuid);
        return market.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(description = "Creates a new market")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Market created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Market.class))),
            @ApiResponse(responseCode = "400", description = "Request content is invalid", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content)
    })
    public ResponseEntity<Market> save(@RequestBody @Valid MarketRegisterDTO data) {
        Market market = new Market();
        market.setName(data.name());
        market.setAddress(data.address());
        market.setImage(data.image());

        service.save(market);

        URI uri = URI.create("/market/" + market.getId());
        return ResponseEntity.created(uri).body(market);
    }

    @PutMapping
    @Operation(description = "Updates an existing market")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Market updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Market.class))),
            @ApiResponse(responseCode = "400", description = "Request content is invalid", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Market not found", content = @Content)
    })
    public ResponseEntity<Market> update(@RequestBody @Valid MarketUpdateDTO data) {
        Market market = service.findById(data.id()).orElseThrow(EntityNotFoundException::new);
        market.setName(data.name());
        market.setAddress(data.address());
        market.setImage(data.image());

        service.save(market);
        return ResponseEntity.ok(market);
    }

    @DeleteMapping
    @Operation(description = "Deletes an existing market")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Market deleted", content = @Content),
            @ApiResponse(responseCode = "400", description = "Request content is invalid", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content)
    })
    public ResponseEntity<Void> deleteById(@RequestBody @Valid IdDTO data) {
        service.deleteById(data.id());
        return ResponseEntity.noContent().build();
    }
}
