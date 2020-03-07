package baltic.amadeus.pizzacooker.unit.dto;

import baltic.amadeus.pizzacooker.dto.ProductDetails;
import baltic.amadeus.pizzacooker.dto.RecipeDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RecipeDetailsTest {
    @Test
    public void newRecipeDetails() {
        Set<ProductDetails> receipePoductsDetails = new HashSet<>();
        receipePoductsDetails.add(new ProductDetails("dough",1));
        receipePoductsDetails.add(new ProductDetails("cheese",1));
        receipePoductsDetails.add(new ProductDetails("salemi",1));
        RecipeDetails recipeDetails = new RecipeDetails(1,"Margarita", receipePoductsDetails);

        assertEquals(new Integer(1), recipeDetails.getId());
        assertEquals("Margarita", recipeDetails.getName());
        assertEquals(3, recipeDetails.getProductsDetails().size());
    }
}
