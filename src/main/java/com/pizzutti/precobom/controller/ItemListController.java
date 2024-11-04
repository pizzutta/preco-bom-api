package com.pizzutti.precobom.controller;

import com.pizzutti.precobom.dto.IdDTO;
import com.pizzutti.precobom.dto.ItemListRegisterDTO;
import com.pizzutti.precobom.dto.ItemListUpdateDTO;
import com.pizzutti.precobom.model.GroceryList;
import com.pizzutti.precobom.model.ItemList;
import com.pizzutti.precobom.service.GroceryListService;
import com.pizzutti.precobom.service.ItemListService;
import com.pizzutti.precobom.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/item-list")
public class ItemListController {

    @Autowired
    private ItemListService service;
    @Autowired
    private GroceryListService groceryListService;
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ItemListRegisterDTO data) {
        ItemList itemList = new ItemList();
        itemList.setProduct(productService.findById(data.productId()).get());
        itemList.setQuantity(data.quantity());
        itemList.setChecked(data.checked());
        service.save(itemList);

        GroceryList groceryList = groceryListService.findById(data.groceryListId()).get();
        groceryList.getItems().add(itemList);
        groceryListService.save(groceryList);

        URI uri = URI.create("/item-list/" + itemList.getId());
        return ResponseEntity.created(uri).body(itemList);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody ItemListUpdateDTO data) {
        ItemList itemList = service.findById(data.id()).orElseThrow(EntityNotFoundException::new);
        itemList.setQuantity(data.quantity());
        itemList.setChecked(data.checked());

        service.save(itemList);
        return ResponseEntity.ok(itemList);
    }

    @DeleteMapping
    public ResponseEntity deleteById(@RequestBody @Valid IdDTO data) {
        service.deleteById(data.id());
        return ResponseEntity.noContent().build();
    }
}
