package com.pizzutti.precobom.repository;

import com.pizzutti.precobom.model.GroceryList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroceryListRepository extends JpaRepository<GroceryList, Long> {

    Optional<GroceryList> findByUuid(UUID uuid);

    List<GroceryList> findByUserIdOrderByIdDesc(Long userId);
}
