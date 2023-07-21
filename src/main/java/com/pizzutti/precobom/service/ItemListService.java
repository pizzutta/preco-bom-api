package com.pizzutti.precobom.service;

import com.pizzutti.precobom.model.ItemList;
import com.pizzutti.precobom.repository.ItemListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemListService {

    @Autowired
    private ItemListRepository repository;

    public Optional<ItemList> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public void save(ItemList itemList) {
        repository.save(itemList);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
