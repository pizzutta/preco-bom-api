package com.pizzutti.precobom.controller;

import com.pizzutti.precobom.dto.MarketRegisterDTO;
import com.pizzutti.precobom.model.Market;
import com.pizzutti.precobom.service.MarketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private MarketService service;

    @GetMapping
    public ResponseEntity getAllMarkets() {
        List<Market> products = service.findAll();
        return (products.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity getMarket(@PathVariable(value = "id") Long id) {
        Optional<Market> product = service.findById(id);
        return (product.isEmpty()) ? ResponseEntity.notFound().build() : ResponseEntity.ok(product.get());
    }

    @PostMapping
    public ResponseEntity saveMarket(@RequestBody @Valid MarketRegisterDTO data) {
        Market market = new Market();
        market.setName(data.name());
        market.setAddress(data.address());
        market.setImage(data.image());

        service.save(market);

        return ResponseEntity.created(URI.create("/market/" + market.getId())).build();
    }

    @PutMapping
    public ResponseEntity updateMarket(@RequestBody Market market) {
        service.save(market);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteMarket(@RequestBody Market market) {
        service.delete(market);
        return ResponseEntity.ok().build();
    }
}
