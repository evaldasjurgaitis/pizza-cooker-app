package baltic.amadeus.pizzacooker.unit.dto;

import baltic.amadeus.pizzacooker.dto.ProductDetails;
import baltic.amadeus.pizzacooker.dto.StockDetails;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class StockDetailsTest {
    @Test
    public void newStockDetails() {
        Set<ProductDetails> stockProductsDetails = new HashSet<>();
        stockProductsDetails.add(new ProductDetails("dough", 10));
        stockProductsDetails.add(new ProductDetails("cheese", 10));
        stockProductsDetails.add(new ProductDetails("salemi", 10));
        StockDetails stockDetails = new StockDetails(1, "Alfa-stock", stockProductsDetails);

        assertEquals("Alfa-stock", stockDetails.getName());
        assertEquals(3, stockDetails.getProductsDetails().size());
    }
}
