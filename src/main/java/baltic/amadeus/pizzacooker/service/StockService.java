package baltic.amadeus.pizzacooker.service;

import baltic.amadeus.pizzacooker.dto.PizzaType;
import baltic.amadeus.pizzacooker.dto.ProductDetails;
import baltic.amadeus.pizzacooker.dto.StockDetails;

import java.util.List;
import java.util.Set;

public interface StockService {
    Set<ProductDetails> getReducedProducts(Integer cookerMachineId, Integer recipeId, PizzaType pizzaType);
}
