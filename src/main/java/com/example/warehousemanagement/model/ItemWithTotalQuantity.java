package com.example.warehousemanagement.model;

public class ItemWithTotalQuantity {

    private Item item;
    private int totalQuantity;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
