import org.example.Product;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    private Product product;

    @BeforeEach
    public void setup() {
        product = new Product(1, "Laptop", 1000.0, 10, "Electronics");
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(1, product.getID());
        assertEquals("Laptop", product.getName());
        assertEquals(1000.0, product.getPrice());
        assertEquals(10, product.getStockQuantity());
        assertEquals("Electronics", product.getCategory());
    }

    @Test
    public void testSetNameAndPrice() {
        product.setName("Tablet");
        product.setPrice(500.0);
        assertEquals("Tablet", product.getName());
        assertEquals(500.0, product.getPrice());
    }

    @Test
    public void testUpdateStockPositive() {
        product.updateStock(5);
        assertEquals(15, product.getStockQuantity());
    }

    @Test
    public void testUpdateStockNegativeValid() {
        product.updateStock(-5);
        assertEquals(5, product.getStockQuantity());
    }

    @Test
    public void testUpdateStockNegativeInvalid() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.updateStock(-11);
        });
        assertEquals("Stock quantity cannot be negative", exception.getMessage());
    }

    @Test
    public void testBuyValidQuantity() {
        double total = product.buy(3);
        assertEquals(7, product.getStockQuantity());
        assertEquals(3000.0, total);
    }

    @Test
    public void testBuyInvalidQuantityZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.buy(0);
        });
        assertEquals("Invalid quantity", exception.getMessage());
    }

    @Test
    public void testBuyInvalidQuantityExceedsStock() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            product.buy(15);
        });
        assertEquals("Invalid quantity", exception.getMessage());
    }
}
