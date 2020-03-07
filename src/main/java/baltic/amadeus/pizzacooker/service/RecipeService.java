package baltic.amadeus.pizzacooker.service;

import baltic.amadeus.pizzacooker.dto.RecipeDetails;

import java.util.List;
import java.util.Map;

public interface RecipeService {
    RecipeDetails save(RecipeDetails recipeDetails);

    RecipeDetails get(Integer id);

    List<RecipeDetails> list();

    void delete(Integer id);

    Map<String, Integer> getRecipeProductsMap(Integer recipeId);
}
