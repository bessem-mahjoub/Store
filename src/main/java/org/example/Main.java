package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // ==============================================
        // QUESTION 1: Basic Classes - Product
        // ==============================================
        System.out.println("\n\n=== QUESTION 1 ===\n\n");

        // 1a) Product Class Creation
        Product laptop = new Product(1, "Dell G15", 1499.99, 50, "PC");
        System.out.println("1a) Test toString(): " + laptop.toString());

        // 1b) updateStock()
        System.out.println("\n1b) Test updateStock():");
        laptop.updateStock(-20);
        System.out.println("Stock after -20: " + laptop.getStockQuantity());
        // We Should update stock without going negative

        try {
            laptop.updateStock(-100);
        } catch (IllegalArgumentException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        // 1c) buy() method with total price calculation
        System.out.println("\n1c) Test buy():");
        double totalPrice = laptop.buy(3);
        System.out.println("Total price for 3 units: " + totalPrice + "€");
        System.out.println("Remaining stock: " + laptop.getStockQuantity());

        // 1d) Complete test
        System.out.println("\n1d) Full test:");
        Product mouse = new Product(2, "MSI", 809.99, 100, "Accessories");
        System.out.println(mouse);
        mouse.buy(2);
        System.out.println("After purchase: " + mouse.getStockQuantity());

        // ==============================================
        // QUESTION 2: Store Implementation
        // ==============================================
        System.out.println("\n\n=== QUESTION 2 ===\n\n");

        // 2a) Store class creation
        Store store = new Store();
        System.out.println("2a) Store created" );

        // Add products
        store.addProduct(laptop);
        store.addProduct(mouse);
        store.addProduct(new Product(3, "USB Cable", 12.99, 200, "Accessories"));

        // 2b) Display products with stock limit
        System.out.println("\n2b) Displaying products (max 10 visible):");
        store.displayProducts(10);

        // 2c) buyProduct() method
        System.out.println("\n2c) Test buyProduct():");
        double order1 = store.buyProduct(1, 2);
        double order2 = store.buyProduct(2, 5);
        System.out.println("Total of orders: " + (order1 + order2) + "€");

        // 2d) Final store state
        System.out.println("\n2d) Final store state:");
        System.out.println(store.toString());
        System.out.println("Products after purchase:");
        store.displayProducts(100);

        // ==============================================
        // QUESTION 3: Event-based Organization
        // ==============================================
        System.out.println("\n\n=== QUESTION 3 ===\n\n");

        // 3a) Factory and event setup
        Factory factory = new Factory();
        store.setFactory(factory);
        System.out.println("3a) Factory configured");

        // 3b) Newsletter system
        Customer bessem = new Customer("Bessem", "bessem@email.com", List.of("PC"));
        Customer yassin = new Customer("Yassin", "yassin@email.com", List.of("Accessories"));
        store.subscribe(bessem);
        store.subscribe(yassin);
        System.out.println("\n3b) Subscribed customers: Bessem and Yassin ");


        // Adding new products
        System.out.println("\nAdding new products:");
        store.addProduct(new Product(4, "MacBook Pro", 1999.99, 10, "PC"));
        store.addProduct(new Product(5, "HDMI Cable", 15.99, 50, "Accessories"));

        // 3c) automatic restocking
        System.out.println("\n3c) Purchase test with restocking:");
        System.out.println("Stock before purchase:");
        store.displayProducts(100);

        // Purchase that drops stock below threshold
        store.buyProduct(4, 10); // Should trigger restocking

        System.out.println("\nStock after purchase and restocking:");
        store.displayProducts(100);

        // Final income check
        System.out.println("\nFinal store state:");
        System.out.println(store.toString());
    }
}
