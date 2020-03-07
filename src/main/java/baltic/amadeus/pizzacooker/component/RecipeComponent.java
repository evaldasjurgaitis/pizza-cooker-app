package baltic.amadeus.pizzacooker.component;

import baltic.amadeus.pizzacooker.dto.RecipeDetails;
import baltic.amadeus.pizzacooker.entity.Recipe;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class RecipeComponent {
    private static ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ProductComponent productComponent;

    public RecipeDetails convertToDto(Recipe recipe) {
        RecipeDetails recipeDetails = modelMapper.map(recipe, RecipeDetails.class);
        recipeDetails.setProductsDetails(productComponent.convertToDto(recipe.getProducts()));
        return recipeDetails;
    }

    public Recipe convertToEntity(RecipeDetails recipeDetails) {
        Recipe recipe = modelMapper.map(recipeDetails, Recipe.class);
        recipe.setProducts(productComponent.convertToEntity(recipeDetails.getProductsDetails()));
        return recipe;
    }

    public List<RecipeDetails> convertToDto(List<Recipe> recipes) {
        return recipes.stream()
                .map(recipe -> convertToDto(recipe))
                .collect(Collectors.toList());
    }
}
