package baltic.amadeus.pizzacooker.service;

import baltic.amadeus.pizzacooker.dto.PizzaType;
import baltic.amadeus.pizzacooker.dto.ProductDetails;

import baltic.amadeus.pizzacooker.component.StockComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    CookerMachineService cookerMachineService;

    @Autowired
    RecipeService recipeService;

    @Autowired
    StockComponent stockComponent;

    public Set<ProductDetails> getReducedProducts(Integer cookerMachineId, Integer recipeId, PizzaType pizzaType) {
        Map<String, Integer> cookerMachineProductsMap = cookerMachineService.getCookerMachineProductsMap(cookerMachineId);
        Set<ProductDetails> recipeProducts = recipeService.get(recipeId).getProductsDetails();
        reduceAmount(cookerMachineProductsMap, recipeProducts, pizzaType);
        return cookerMachineProductsMap.keySet()
                .stream().map(productName -> new ProductDetails(productName, cookerMachineProductsMap.get(productName)))
                .collect(Collectors.toSet());
    }

    private void reduceAmount(Map<String, Integer> cookerMachineProductsMap, Set<ProductDetails> recipeProducts, PizzaType pizzaType) {
        recipeProducts.stream()
                .forEach(product -> cookerMachineProductsMap.replace(product.getName(), cookerMachineProductsMap.get(product.getName()) - (product.getQty() * pizzaType.getValue())));
    }
}
