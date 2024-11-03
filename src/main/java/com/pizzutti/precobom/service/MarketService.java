package com.pizzutti.precobom.service;

import com.pizzutti.precobom.model.Market;
import com.pizzutti.precobom.repository.MarketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketService {

    @Autowired
    private MarketRepository repository;

    public Optional<Market> findById(Long id) {
        return repository.findById(id);
    }

    public List<Market> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void save(Market product) {
        repository.save(product);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
