package com.pizzutti.precobom.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity(name = "tb_item_list")
@Schema(description = "An item list object contains information about an item of a grocery list in the app")
public class ItemList {

    @Id
    @GeneratedValue
    @Schema(description = "The item list ID", example = "1")
    private Long id;
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
