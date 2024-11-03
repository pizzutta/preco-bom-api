package com.pizzutti.precobom.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzutti.precobom.dto.IdDTO;
import com.pizzutti.precobom.dto.ProductRegisterDTO;
import com.pizzutti.precobom.dto.ProductUpdateDTO;
import com.pizzutti.precobom.model.Product;
import com.pizzutti.precobom.service.MarketService;
import com.pizzutti.precobom.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;
    @Autowired
    private MarketService marketService;

    @GetMapping
    public ResponseEntity getAllProducts() {
        List<Product> products = service.findAll();
        return (products.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(products);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getSortedProducts(@RequestParam(name = "order") String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.readValue(json, Map.class);
            Order order = new Order(Direction.valueOf(map.get("direction")), map.get("property"));
            List<Product> products = service.findAll(Sort.by(order));
            return (products.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(products);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getProduct(@PathVariable(value = "id") Long id) {
        Optional<Product> product = service.findById(id);
        return (product.isEmpty()) ? ResponseEntity.notFound().build() : ResponseEntity.ok(product.get());
    }

    @GetMapping("/market/{marketId}")
    public ResponseEntity getProductsByMarketId(@PathVariable(value = "marketId") Long marketId) {
        List<Product> products = service.findByMarketId(marketId);
        return (products.isEmpty()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity saveProduct(@RequestBody @Valid ProductRegisterDTO data) {
        Product product = new Product();
        product.setName(data.name());
        product.setPrice(data.price());
        product.setMeasuringUnit(data.measuringUnit());
        product.setImage(data.image());
        product.setMarket(marketService.findById(data.marketId()).get());

        service.save(product);

        URI uri = URI.create("/product/" + product.getId());
        return ResponseEntity.created(uri).body(product);
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody @Valid ProductUpdateDTO data) {
        Product product = service.findById(data.id()).orElseThrow(EntityNotFoundException::new);
        product.setName(data.name());
        product.setPrice(data.price());
        product.setMeasuringUnit(data.measuringUnit());
        product.setImage(data.image());
        product.setMarket(marketService.findById(data.marketId()).get());

        service.save(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping
    public ResponseEntity deleteProductById(@RequestBody @Valid IdDTO data) {
        service.deleteById(data.id());
        return ResponseEntity.noContent().build();
    }
}
