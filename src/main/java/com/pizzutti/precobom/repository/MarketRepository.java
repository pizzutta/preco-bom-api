package com.pizzutti.precobom.repository;

import com.pizzutti.precobom.model.Market;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MarketRepository extends JpaRepository<Market, Long> {

    Optional<Market> findByUuid(UUID uuid);

    List<Market> findByNameContainingIgnoreCase(String name);
}
