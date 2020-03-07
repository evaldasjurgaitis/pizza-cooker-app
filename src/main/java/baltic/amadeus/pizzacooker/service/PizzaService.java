package baltic.amadeus.pizzacooker.service;

import baltic.amadeus.pizzacooker.dto.PizzaDetails;

public interface PizzaService {
    PizzaDetails getPizza(Integer machineId, Integer recipeId, Integer size);
}
