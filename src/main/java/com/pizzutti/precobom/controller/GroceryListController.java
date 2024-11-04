package com.pizzutti.precobom.controller;

import com.pizzutti.precobom.dto.GroceryListRegisterDTO;
import com.pizzutti.precobom.dto.GroceryListUpdateDTO;
import com.pizzutti.precobom.dto.IdDTO;
import com.pizzutti.precobom.model.GroceryList;
import com.pizzutti.precobom.service.GroceryListService;
import com.pizzutti.precobom.service.UserService;
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
@RequestMapping("/grocery-list")
@Tag(name = "Grocery List")
public class GroceryListController {

    @Autowired
    private GroceryListService service;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @Operation(description = "Gets a grocery list by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grocery list found",
                    content = @Content(mediaType = "application/json", schema =
                    @Schema(implementation = GroceryList.class))),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Grocery list not found", content = @Content)
    })
    public ResponseEntity getById(@Parameter(description = "The grocery list ID", example = "1") @PathVariable(value = "id"
    ) Long id) {
        Optional<GroceryList> groceryList = service.findById(id);
        return (groceryList.isEmpty()) ? ResponseEntity.notFound().build() : ResponseEntity.ok(groceryList.get());
    }

    @GetMapping("/user/{userId}")
    @Operation(description = "Gets all the user grocery lists by the user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grocery lists found",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(
                            schema = @Schema(implementation = GroceryList.class)))),
            @ApiResponse(responseCode = "204", description = "No grocery lists were found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content)
    })
    public ResponseEntity getByUser(@Parameter(description = "The user ID", example = "1") @PathVariable(value = "userId") Long userId) {
        List<GroceryList> groceryLists = service.findByUserId(userId);
        return (groceryLists.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(groceryLists);
    }

    @PostMapping
    @Operation(description = "Creates a new grocery list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Grocery list created",
                    content = @Content(mediaType = "application/json", schema =
                    @Schema(implementation = GroceryList.class))),
            @ApiResponse(responseCode = "400", description = "Request content is invalid", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content)
    })
    public ResponseEntity save(@RequestBody @Valid GroceryListRegisterDTO data) {
        GroceryList groceryList = new GroceryList();
        groceryList.setName(data.name());
        groceryList.setUser(userService.findById(data.userId()).get());

        service.save(groceryList);

        URI uri = URI.create("/grocery-list/" + groceryList.getId());
        return ResponseEntity.created(uri).body(groceryList);
    }

    @PutMapping
    @Operation(description = "Updates an existing grocery list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grocery list updated",
                    content = @Content(mediaType = "application/json", schema =
                    @Schema(implementation = GroceryList.class))),
            @ApiResponse(responseCode = "400", description = "Request content is invalid", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Grocery list not found", content = @Content)
    })
    public ResponseEntity update(@RequestBody @Valid GroceryListUpdateDTO data) {
        GroceryList groceryList = service.findById(data.id()).orElseThrow(EntityNotFoundException::new);
        groceryList.setName(data.name());

        service.save(groceryList);
        return ResponseEntity.ok(groceryList);
    }

    @DeleteMapping
    @Operation(description = "Deletes an existing grocery list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Grocery list deleted", content = @Content),
            @ApiResponse(responseCode = "400", description = "Request content is invalid", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content)
    })
    public ResponseEntity deleteById(@RequestBody @Valid IdDTO data) {
        service.deleteById(data.id());
        return ResponseEntity.noContent().build();
    }
}
