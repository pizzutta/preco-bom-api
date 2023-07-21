package com.pizzutti.precobom.repository;

import com.pizzutti.precobom.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    List<Product> findAll(Sort sort);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByMarketId(Long marketId);
}
