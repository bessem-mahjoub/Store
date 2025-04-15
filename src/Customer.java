import java.util.List;

public class Customer {
    private String name;
    private String email;
    private List<String> interests;

    public Customer(String name, String email, List<String> interests) {
        this.name = name;
        this.email = email;
        this.interests = interests;
    }

    public void notifyNewProduct(Product product) {
        if (interests.contains(product.getCategory())) {
            System.out.println("[NEWSLETTER] " + name + " (" + email + "): " +
                    "New product is now Available - " + product.getName());
        }
    }

    // Getters
    public String getEmail() { return email; }
}