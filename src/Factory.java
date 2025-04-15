public class Factory {
    private static final int RESTOCK_AMOUNT = 50; // Quantité fixe pour réapprovisionner

    public void handleOutOfStock(OutOfStockEvent event) {
        Product product = event.getProduct();
        product.updateStock(RESTOCK_AMOUNT);
        System.out.println("[FACTORY] Restocked " + RESTOCK_AMOUNT +
                " units of " + product.getName());
    }
}