package com.pizzutti.precobom.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "tb_product")
@Schema(description = "A product object contains information about a product registered by some market in the app")
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Schema(description = "The product ID", example = "1")
    private Long id;
    @UuidGenerator
    @Schema(description = "The product UUID", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID uuid;
    @Column
    @Schema(description = "The product's name", example = "Grape Tomatoes")
    private String name;
    @Column
    @Schema(description = "The product's price determined by the market", example = "2.25")
    private Double price;
    @Column(name = "measuring_unit")
    @Schema(description = "The unit used to measure the product", example = "lbs")
    private String measuringUnit;
    @Column
    @Schema(description = "An URL or a path to an image provided by the market to represent the product",
            example = "https://mysite.com/images/my-product.jpg")
    private String image;
    @ManyToOne
    @JoinColumn(name = "market_id")
    @JsonManagedReference
    @Schema(description = "The market that registered that product")
    private Market market;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMeasuringUnit() {
        return measuringUnit;
    }

    public void setMeasuringUnit(String measuringUnit) {
        this.measuringUnit = measuringUnit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }
}
