import org.example.*;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class StoreTest {

    private Store store;
    private Factory mockFactory;

    @BeforeEach
    public void setup() {
        store = new Store();
        mockFactory = mock(Factory.class); // simule l'objet Factory
        store.setFactory(mockFactory);     // injecte le mock dans Store
    }

    @Test
    public void testBuyProduct_triggersOutOfStockEvent() {
        // Arrange
        Product product = new Product(1, "Keyboard", 50.0, 9, "Electronics"); // stock < 10
        store.addProduct(product);

        // Act
        double totalPrice = store.buyProduct(1, 1);

        // Assert
        assertEquals(50.0, totalPrice);
        assertEquals(8, product.getStockQuantity());
        verify(mockFactory, times(1)).handleOutOfStock(any(OutOfStockEvent.class));
    }

    @Test
    public void testAddProduct_notifiesSubscribers() {
        Customer mockCustomer = mock(Customer.class);
        store.subscribe(mockCustomer);

        Product product = new Product(2, "Mouse", 25.0, 100, "Electronics");
        store.addProduct(product);

        verify(mockCustomer, times(1)).notifyNewProduct(product);
    }

    @Test
    public void testBuyProduct_productNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            store.buyProduct(99, 1); // id inexistant
        });
        assertEquals("Product not found!", exception.getMessage());
    }
}
