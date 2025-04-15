package org.example;

public class Product {
    private final int ID;
    private String name;
    private double price;
    private int stockQuantity;
    private String category;

    public String getCategory() {
        return category;
    }

    ///////// *a* Product Constructor /////////
    public Product(int ID, String name, double price, int stockQuantity, String category) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }

    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getStockQuantity() {
        return stockQuantity;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    ///////// *b* Update stock /////////
    public void updateStock(int quantity) {
        if (this.stockQuantity + quantity >= 0) {
            this.stockQuantity += quantity;
        } else {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
    }

    ///////// *c* Buy method /////////
    public double buy(int quantity) {
        if (quantity <= 0 || quantity > this.stockQuantity) {
            throw new IllegalArgumentException("Invalid quantity");
        }
        this.stockQuantity -= quantity;
        return quantity * this.price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}
