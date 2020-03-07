package baltic.amadeus.pizzacooker.unit.entity;

import baltic.amadeus.pizzacooker.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProductTest {
    @Test
    public void newProduct() {
        Product product = new Product("cheese", 0);
        assertEquals("cheese", product.getName());
        assertEquals(new Integer(0), product.getQty());
    }
}
