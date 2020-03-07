package baltic.amadeus.pizzacooker.unit.dto;

import baltic.amadeus.pizzacooker.dto.CookerMachineDetails;
import baltic.amadeus.pizzacooker.dto.ProductDetails;
import baltic.amadeus.pizzacooker.dto.StockDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CookerMachineDetailsTest {
    @Test
    public void newCookerMachineDetails() {
        Set<ProductDetails> stockProductsDetails = new HashSet<>();
        stockProductsDetails.add(new ProductDetails("dough", 10));
        stockProductsDetails.add(new ProductDetails("cheese", 10));
        stockProductsDetails.add(new ProductDetails("salemi", 10));
        StockDetails stockDetails = new StockDetails(1, "Alfa-stock", stockProductsDetails);
        CookerMachineDetails cookerMachineDetails = new CookerMachineDetails(1, "Alfa", 10,stockDetails, true);

        assertEquals("Alfa", cookerMachineDetails.getName());
        assertEquals("Alfa-stock", cookerMachineDetails.getStockDetails().getName());
        assertEquals(3, cookerMachineDetails.getStockDetails().getProductsDetails().size());
        assertEquals(new Integer(10), cookerMachineDetails.getCookCount());
    }
}
