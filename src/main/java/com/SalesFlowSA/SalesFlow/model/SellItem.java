package com.SalesFlowSA.SalesFlow.model;

import jakarta.persistence.*;

@Entity
public class SellItem {

    public SellItem() {
    }

    public SellItem(Item item, float quantity) {
        this.item = item;
        this.quantity = quantity;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private float quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
