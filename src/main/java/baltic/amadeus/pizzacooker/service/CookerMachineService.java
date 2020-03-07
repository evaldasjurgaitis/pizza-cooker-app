package baltic.amadeus.pizzacooker.service;

import baltic.amadeus.pizzacooker.dto.PizzaType;
import baltic.amadeus.pizzacooker.dto.CookerMachineDetails;

import java.util.List;
import java.util.Map;

public interface CookerMachineService {
    CookerMachineDetails save(CookerMachineDetails cookerMachineDetails);

    CookerMachineDetails get(Integer id);

    List<CookerMachineDetails> list();

    void delete(Integer id);

    CookerMachineDetails cleanUp(Integer id);

    CookerMachineDetails updateCookCount(Integer cookerMachineId);

    CookerMachineDetails updateReducedProducts(Integer cookerMachineId, Integer recipeId, PizzaType pizzaType);

    Map<String, Integer> getCookerMachineProductsMap(Integer cookerMachineId);

    public CookerMachineDetails update(CookerMachineDetails cookerMachineDetails);

}
