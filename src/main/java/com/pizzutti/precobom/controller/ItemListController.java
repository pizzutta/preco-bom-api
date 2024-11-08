package com.pizzutti.precobom.controller;

import com.pizzutti.precobom.dto.IdDTO;
import com.pizzutti.precobom.dto.ItemListRegisterDTO;
import com.pizzutti.precobom.dto.ItemListUpdateDTO;
import com.pizzutti.precobom.model.GroceryList;
import com.pizzutti.precobom.model.ItemList;
import com.pizzutti.precobom.service.GroceryListService;
import com.pizzutti.precobom.service.ItemListService;
import com.pizzutti.precobom.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
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

@RestController
@RequestMapping("/item-list")
@Tag(name = "Item List")
public class ItemListController {

    @Autowired
    private ItemListService service;
    @Autowired
    private GroceryListService groceryListService;
    @Autowired
    private ProductService productService;

    @PostMapping
    @Operation(description = "Creates a new item list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item list created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemList.class))),
            @ApiResponse(responseCode = "400", description = "Request content is invalid", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content)
    })
    public ResponseEntity<ItemList> save(@RequestBody @Valid ItemListRegisterDTO data) {
        ItemList itemList = new ItemList();
        itemList.setProduct(productService.findById(data.productId()).orElseThrow(EntityNotFoundException::new));
        itemList.setQuantity(data.quantity());
        itemList.setChecked(data.checked());
        service.save(itemList);

        GroceryList groceryList =
                groceryListService.findById(data.groceryListId()).orElseThrow(EntityNotFoundException::new);
        groceryList.getItems().add(itemList);
        groceryListService.save(groceryList);

        URI uri = URI.create("/item-list/" + itemList.getId());
        return ResponseEntity.created(uri).body(itemList);
    }

    @PutMapping
    @Operation(description = "Updates an existing item list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item list updated",
                    content = @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ItemList.class))),
            @ApiResponse(responseCode = "400", description = "Request content is invalid", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item list not found", content = @Content)
    })
    public ResponseEntity<ItemList> update(@RequestBody ItemListUpdateDTO data) {
        ItemList itemList = service.findById(data.id()).orElseThrow(EntityNotFoundException::new);
        itemList.setQuantity(data.quantity());
        itemList.setChecked(data.checked());

        service.save(itemList);
        return ResponseEntity.ok(itemList);
    }

    @DeleteMapping
    @Operation(description = "Deletes an existing item list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item list deleted", content = @Content),
            @ApiResponse(responseCode = "400", description = "Request content is invalid", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthenticated/unauthorized", content = @Content)
    })
    public ResponseEntity<Void> deleteById(@RequestBody @Valid IdDTO data) {
        service.deleteById(data.id());
        return ResponseEntity.noContent().build();
    }
}
