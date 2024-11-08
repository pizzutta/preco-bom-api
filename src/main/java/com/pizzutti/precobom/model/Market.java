package com.pizzutti.precobom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "tb_market")
@Schema(description = "A market object contains information about a market registered in the app")
public class Market {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Schema(description = "The market ID", example = "1")
    private Long id;
    @UuidGenerator
    @Schema(description = "The market UUID", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID uuid;
    @Column
    @Schema(description = "The market's name", example = "QuickCart")
    private String name;
    @Column
    @Schema(description = "The market's address", example = "123 Oak Ave, Everdale, ZIP 54321")
    private String address;
    @Column
    @Schema(description = "An URL or a path to an image provided by the market to represent it",
            example = "https://mysite.com/images/my-logo.jpg")
    private String image;
    @OneToMany(fetch = LAZY, mappedBy = "market")
    @JsonBackReference
    @Schema(description = "A list of product objects registered by the market")
    private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
