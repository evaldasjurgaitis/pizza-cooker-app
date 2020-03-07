package baltic.amadeus.pizzacooker.unit.entity;

import baltic.amadeus.pizzacooker.entity.Product;
import baltic.amadeus.pizzacooker.entity.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class StockTest {

    @Test
    public void newStock() {
        Set<Product> stock_products = new HashSet<>();
        stock_products.add(new Product("dough", 10));
        stock_products.add(new Product("cheese", 10));
        stock_products.add(new Product("salemi", 10));
        Stock stock = new Stock(1, "Alfa-stock", stock_products);

        assertEquals("Alfa-stock", stock.getName());
        assertEquals(3, stock.getProducts().size());
    }
}
