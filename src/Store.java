import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Product> products;
    private List<String> pastOrders;
    private double totalIncome;
    private Factory factory;
    private List<Customer> subscribers = new ArrayList<>();
    private int stockThreshold = 10;

    public Store() {
        this.products = new ArrayList<>();
        this.pastOrders = new ArrayList<>();
        this.totalIncome = 0.0;
    }

    // Getters
    public List<Product> getProducts() { return products; }
    public double getTotalIncome() { return totalIncome; }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public void addProduct(Product product) {
        products.add(product);
        notifySubscribers(product);
    }

    private void notifySubscribers(Product product) {
        for (Customer subscriber : subscribers) {
            subscriber.notifyNewProduct(product);
        }
    }

    public void subscribe(Customer customer) {
        subscribers.add(customer);
    }

    public double buyProduct(int productId, int quantity) {
        for (Product p : products) {
            if (p.getID() == productId) {
                double totalPrice = p.buy(quantity);
                totalIncome += totalPrice;
                pastOrders.add("Order: " + quantity + "x " + p.getName());

                if (p.getStockQuantity() < stockThreshold) {
                    OutOfStockEvent event = new OutOfStockEvent(p, p.getStockQuantity());
                    factory.handleOutOfStock(event);
                }
                return totalPrice;
            }
        }
        throw new IllegalArgumentException("Product not found!");
    }

    public void displayProducts(int maxVisibleStock) {
        for (Product p : products) {
            int displayStock = Math.min(p.getStockQuantity(), maxVisibleStock);
            System.out.println(p.getName() + " - Price: " + p.getPrice() +
                    "€ - Stock: " + displayStock);
        }
    }

    @Override
    public String toString() {
        return String.format("Store [Products: %d, Total Income: %.2f€]",
                products.size(), totalIncome);
    }
}