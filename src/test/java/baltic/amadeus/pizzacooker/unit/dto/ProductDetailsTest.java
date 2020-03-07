package baltic.amadeus.pizzacooker.unit.dto;

import baltic.amadeus.pizzacooker.dto.ProductDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProductDetailsTest {
    @Test
    public void newProductDetails() {
        ProductDetails productDetails = new ProductDetails("cheese", 0);
        assertEquals("cheese", productDetails.getName());
        assertEquals(new Integer(0), productDetails.getQty());
    }
}
