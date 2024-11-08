package com.pizzutti.precobom.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "tb_item_list")
@Schema(description = "An item list object contains information about an item of a grocery list in the app")
public class ItemList {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Schema(description = "The item list ID", example = "1")
    private Long id;
    @UuidGenerator
    @Schema(description = "The item list UUID", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID uuid;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @Schema(description = "The product that is contained in the grocery list")
    private Product product;
    @Column
    @Schema(description = "The quantity defined by the user for that product", example = "5")
    private Integer quantity;
    @Column
    @Schema(description = "If true, the item has already been checked on the grocery list")
    private Boolean checked;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
