package baltic.amadeus.pizzacooker.unit.entity;

import baltic.amadeus.pizzacooker.entity.Product;
import baltic.amadeus.pizzacooker.entity.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RecipeTest {

    @Test
    public void newRecipe() {
        Set<Product> receipePoducts = new HashSet<>();
        receipePoducts.add(new Product("dough",1));
        receipePoducts.add(new Product("cheese",1));
        receipePoducts.add(new Product("salemi",1));
        Recipe recipe = new Recipe(1,"Margarita", receipePoducts);

        assertEquals(new Integer(1), recipe.getId());
        assertEquals("Margarita", recipe.getName());
        assertEquals(3, recipe.getProducts().size());
    }
}
