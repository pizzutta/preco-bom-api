package com.pizzutti.precobom.repository;

import com.pizzutti.precobom.model.Market;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketRepository extends JpaRepository<Market, Long> {

    List<Market> findByNameContainingIgnoreCase(String name);
}
