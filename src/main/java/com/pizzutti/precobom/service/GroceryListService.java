package com.pizzutti.precobom.service;

import com.pizzutti.precobom.model.GroceryList;
import com.pizzutti.precobom.repository.GroceryListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroceryListService {

    @Autowired
    private GroceryListRepository repository;

    public Optional<GroceryList> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<GroceryList> findByUUID(UUID uuid) {
        return repository.findByUuid(uuid);
    }

    public List<GroceryList> findByUserId(Long userId) {
        return repository.findByUserIdOrderByIdDesc(userId);
    }

    @Transactional
    public void save(GroceryList groceryList) {
        repository.save(groceryList);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
