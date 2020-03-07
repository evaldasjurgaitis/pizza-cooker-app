package baltic.amadeus.pizzacooker.service;

import baltic.amadeus.pizzacooker.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;

@Service
public class PizzaServiceImpl implements PizzaService {
    @Autowired
    RecipeService recipeService;

    @Autowired
    CookerMachineService cookerMachineService;

    @Value("${cooker-machine.cook-count.limit}")
    private Integer cookCountLimit;

    public PizzaDetails getPizza(Integer machineId, Integer recipeId, Integer size){
        CookerMachineDetails cookerMachineDetails = cookerMachineService.get(machineId);
        if (cookerMachineDetails.getCookCount() == cookCountLimit) {
            return new PizzaDetails(null, MessageType.CLEAN_COOKER_MACHINE);
        }
        return getPizza(machineId, recipeId, PizzaType.valueOf(size));
    }

    private PizzaDetails getPizza(Integer cookerMachineId, Integer recipeId, PizzaType pizzaType) {
        if (isProducible(cookerMachineId, recipeId, pizzaType)) {
            cookerMachineActions(cookerMachineId, recipeId, pizzaType);
            return new PizzaDetails(new Pizza(pizzaType, recipeService.get(recipeId)), MessageType.PIZZA_PRODUCED);
        }
         return getOffer(cookerMachineId,recipeId,pizzaType);
    }
    private PizzaDetails getOffer(Integer cookerMachineId, Integer recipeId, PizzaType pizzaType) {
        return recipeService.list()
                .stream()
                .filter(recipe -> !recipe.getId().equals(recipeId))
                .filter(recipe -> isProducible(cookerMachineId, recipe.getId(), pizzaType))
                .map(recipe -> getOfferPizza(cookerMachineId, recipe.getId(), pizzaType))
                .findFirst()
                .orElseGet(() -> new PizzaDetails(null, MessageType.NO_ENOUGH_PRODUCTS));
    }

    private PizzaDetails getOfferPizza(Integer cookerMachineId, Integer recipeId, PizzaType pizzaType) {
        cookerMachineActions(cookerMachineId, recipeId, pizzaType);
        return new PizzaDetails(new Pizza(pizzaType, recipeService.get(recipeId)), MessageType.OFFER_PIZZA);
    }

    private boolean isProducible(Integer cookerMachineId, Integer recipeId, PizzaType pizzaType) {
        Map<String, Integer> cookerMachineProductsMap = cookerMachineService.getCookerMachineProductsMap(cookerMachineId);
        Map<String, Integer> recipeProductsMap = recipeService.getRecipeProductsMap(recipeId);
        return recipeProductsMap.entrySet().stream()
                .allMatch(isEnoughProducts(cookerMachineProductsMap, pizzaType));
    }

    private Predicate<Map.Entry<String, Integer>> isEnoughProducts(Map<String, Integer> cookerMachineProductsMap, PizzaType pizzaType) {
        return entry -> cookerMachineProductsMap.containsKey(entry.getKey()) && cookerMachineProductsMap.get(entry.getKey()) >= pizzaType.getValue() * entry.getValue();
    }

    private void cookerMachineActions(Integer cookerMachineId, Integer recipeId, PizzaType pizzaType) {
        cookerMachineService.updateCookCount(cookerMachineId);
        cookerMachineService.updateReducedProducts(cookerMachineId, recipeId, pizzaType);
    }
}
