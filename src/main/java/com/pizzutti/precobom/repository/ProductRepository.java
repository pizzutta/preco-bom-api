package com.pizzutti.precobom.repository;

import com.pizzutti.precobom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByUuid(UUID uuid);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByMarketId(Long marketId);
}
