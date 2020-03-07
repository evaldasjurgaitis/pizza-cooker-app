package baltic.amadeus.pizzacooker.service;

import baltic.amadeus.pizzacooker.dao.RecipeDAO;
import baltic.amadeus.pizzacooker.dto.ProductDetails;
import baltic.amadeus.pizzacooker.dto.RecipeDetails;
import baltic.amadeus.pizzacooker.entity.Recipe;
import baltic.amadeus.pizzacooker.exception.RecipeNotFoundException;
import baltic.amadeus.pizzacooker.component.RecipeComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {
    private static final Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);

    @Autowired
    private RecipeDAO recipeDAO;

    @Autowired
    RecipeComponent recipeComponent;

    public RecipeDetails save(RecipeDetails recipeDetails) {
        Recipe recipe = recipeComponent.convertToEntity(recipeDetails);
        return recipeComponent.convertToDto(recipeDAO.save(recipe));
    }

    public RecipeDetails get(Integer id) {
        Optional<Recipe> recipe = recipeDAO.findById(id);
        return recipeComponent.convertToDto(recipe.orElseThrow(() -> new RecipeNotFoundException(id)));
    }

    public List<RecipeDetails> list() {
        return recipeComponent.convertToDto(recipeDAO.findAll());
    }

    public void delete(Integer id) {
        recipeDAO.deleteById(id);
    }

    public Map<String, Integer> getRecipeProductsMap(Integer recipeId) {
        Set<ProductDetails> recipeProducts = get(recipeId).getProductsDetails();
        return recipeProducts.stream().collect(
                Collectors.toMap(product -> product.getName(), product -> product.getQty()));
    }
}
