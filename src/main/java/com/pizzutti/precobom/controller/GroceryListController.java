package com.pizzutti.precobom.controller;

import com.pizzutti.precobom.dto.GroceryListRegisterDTO;
import com.pizzutti.precobom.dto.IdDTO;
import com.pizzutti.precobom.model.GroceryList;
import com.pizzutti.precobom.service.GroceryListService;
import com.pizzutti.precobom.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grocery-list")
public class GroceryListController {

    @Autowired
    private GroceryListService service;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity getGroceryList(@PathVariable(value = "id") Long id) {
        Optional<GroceryList> product = service.findById(id);
        return (product.isEmpty()) ? ResponseEntity.notFound().build() : ResponseEntity.ok(product.get());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity getGroceryListsByUser(@PathVariable(value = "userId") Long userId) {
        List<GroceryList> groceryLists = service.findByUserId(userId);
        return (groceryLists.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(groceryLists);
    }

    @PostMapping
    public ResponseEntity saveGroceryList(@RequestBody @Valid GroceryListRegisterDTO data) {
        GroceryList groceryList = new GroceryList();
        groceryList.setName(data.name());
        groceryList.setUser(userService.findById(data.userId()).get());

        service.save(groceryList);

        URI uri = URI.create("/grocery-list/" + groceryList.getId());
        return ResponseEntity.created(uri).body(groceryList);
    }

    @PutMapping
    public ResponseEntity updateGroceryList(@RequestBody GroceryList groceryList) {
        service.save(groceryList);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteGroceryListById(@RequestBody IdDTO data) {
        service.deleteById(data.id());
        return ResponseEntity.ok().build();
    }
}
