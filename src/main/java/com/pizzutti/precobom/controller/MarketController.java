package com.pizzutti.precobom.controller;

import com.pizzutti.precobom.dto.IdDTO;
import com.pizzutti.precobom.dto.MarketRegisterDTO;
import com.pizzutti.precobom.dto.MarketUpdateDTO;
import com.pizzutti.precobom.model.Market;
import com.pizzutti.precobom.service.MarketService;
import jakarta.persistence.EntityNotFoundException;
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
    public ResponseEntity getAll() {
        List<Market> markets = service.findAll();
        return (markets.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(markets);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value = "id") Long id) {
        Optional<Market> market = service.findById(id);
        return (market.isEmpty()) ? ResponseEntity.notFound().build() : ResponseEntity.ok(market.get());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid MarketRegisterDTO data) {
        Market market = new Market();
        market.setName(data.name());
        market.setAddress(data.address());
        market.setImage(data.image());

        service.save(market);

        URI uri = URI.create("/market/" + market.getId());
        return ResponseEntity.created(uri).body(market);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody @Valid MarketUpdateDTO data) {
        Market market = service.findById(data.id()).orElseThrow(EntityNotFoundException::new);
        market.setName(data.name());
        market.setAddress(data.address());
        market.setImage(data.image());

        service.save(market);
        return ResponseEntity.ok(market);
    }

    @DeleteMapping
    public ResponseEntity deleteById(@RequestBody @Valid IdDTO data) {
        service.deleteById(data.id());
        return ResponseEntity.noContent().build();
    }
}
