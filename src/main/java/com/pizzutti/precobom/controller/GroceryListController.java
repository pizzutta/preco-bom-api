package com.pizzutti.precobom.controller;

import com.pizzutti.precobom.dto.GroceryListRegisterDTO;
import com.pizzutti.precobom.dto.GroceryListUpdateDTO;
import com.pizzutti.precobom.dto.IdDTO;
import com.pizzutti.precobom.model.GroceryList;
import com.pizzutti.precobom.service.GroceryListService;
import com.pizzutti.precobom.service.UserService;
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
public class GroceryListController {

    @Autowired
    private GroceryListService service;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value = "id") Long id) {
        Optional<GroceryList> groceryList = service.findById(id);
        return (groceryList.isEmpty()) ? ResponseEntity.notFound().build() : ResponseEntity.ok(groceryList.get());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity getByUser(@PathVariable(value = "userId") Long userId) {
        List<GroceryList> groceryLists = service.findByUserId(userId);
        return (groceryLists.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(groceryLists);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid GroceryListRegisterDTO data) {
        GroceryList groceryList = new GroceryList();
        groceryList.setName(data.name());
        groceryList.setUser(userService.findById(data.userId()).get());

        service.save(groceryList);

        URI uri = URI.create("/grocery-list/" + groceryList.getId());
        return ResponseEntity.created(uri).body(groceryList);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody @Valid GroceryListUpdateDTO data) {
        GroceryList groceryList = service.findById(data.id()).orElseThrow(EntityNotFoundException::new);
        groceryList.setName(data.name());

        service.save(groceryList);
        return ResponseEntity.ok(groceryList);
    }

    @DeleteMapping
    public ResponseEntity deleteById(@RequestBody @Valid IdDTO data) {
        service.deleteById(data.id());
        return ResponseEntity.noContent().build();
    }
}
