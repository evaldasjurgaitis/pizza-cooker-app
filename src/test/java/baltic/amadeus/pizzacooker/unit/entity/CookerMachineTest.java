package baltic.amadeus.pizzacooker.unit.entity;

import baltic.amadeus.pizzacooker.entity.CookerMachine;
import baltic.amadeus.pizzacooker.entity.Product;
import baltic.amadeus.pizzacooker.entity.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CookerMachineTest {

    @Test
    public void newCookerMachine() {
        Set<Product> stock_products = new HashSet<>();
        stock_products.add(new Product("dough", 10));
        stock_products.add(new Product("cheese", 10));
        stock_products.add(new Product("salemi", 10));
        Stock stock = new Stock(1, "Alfa-stock", stock_products);
        CookerMachine cookerMachine = new CookerMachine(1, "Alfa", 10, true);
        cookerMachine.setStock(stock);

        assertEquals("Alfa", cookerMachine.getName());
        assertEquals("Alfa-stock", cookerMachine.getStock().getName());
        assertEquals(3, cookerMachine.getStock().getProducts().size());
        assertEquals(new Integer(10), cookerMachine.getCookCount());
    }
}
