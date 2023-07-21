package com.pizzutti.precobom.service;

import com.pizzutti.precobom.model.Product;
import com.pizzutti.precobom.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }


    public List<Product> findByMarketId(Long marketId) {
        return repository.findByMarketId(marketId);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public List<Product> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Transactional
    public void save(Product product) {
        repository.save(product);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
