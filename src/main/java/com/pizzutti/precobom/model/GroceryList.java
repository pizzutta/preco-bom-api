package com.pizzutti.precobom.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "tb_grocery_list")
@Schema(description = "A grocery list object contains information about a grocery list created by some user in the app")
public class GroceryList {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Schema(description = "The grocery list ID", example = "1")
    private Long id;
    @UuidGenerator
    @Schema(description = "The grocery list UUID", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID uuid;
    @Column
    @Schema(description = "The grocery list's name", example = "Weekly Essentials")
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Schema(description = "The user that created and maintains the grocery list")
    private User user;
    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "grocery_list_id")
    @Schema(description = "The items contained in the grocery list")
    private List<ItemList> items = new ArrayList<>();

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ItemList> getItems() {
        return items;
    }

    public void setItems(List<ItemList> items) {
        this.items = items;
    }
}
