package com.SalesFlowSA.SalesFlow.model.DTO;

import com.SalesFlowSA.SalesFlow.model.Item;

public class SellItemDTO {
    private Item item;
    private float quantity;

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
