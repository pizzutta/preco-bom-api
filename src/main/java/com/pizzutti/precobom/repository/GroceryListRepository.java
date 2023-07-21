package com.pizzutti.precobom.repository;

import com.pizzutti.precobom.model.GroceryList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroceryListRepository extends JpaRepository<GroceryList, Long> {

    List<GroceryList> findByUserIdOrderByIdDesc(Long userId);
}
