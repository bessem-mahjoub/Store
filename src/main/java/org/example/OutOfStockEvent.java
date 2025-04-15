package org.example;

public class OutOfStockEvent {
    private Product product;
    private int remainingQuantity;

    public OutOfStockEvent(Product product, int remainingQuantity) {
        this.product = product;
        this.remainingQuantity = remainingQuantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }
}