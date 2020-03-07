package baltic.amadeus.pizzacooker.unit.dto;

import baltic.amadeus.pizzacooker.dto.Pizza;
import baltic.amadeus.pizzacooker.dto.PizzaType;
import baltic.amadeus.pizzacooker.dto.RecipeDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class PizzaTest {

    @Test
    public void newPizza() {
        Pizza pizza = new Pizza(PizzaType.SMALL, new RecipeDetails(1,"Margarita",null));
        assertEquals(PizzaType.SMALL, pizza.getType());
        assertNotNull( pizza.getRecipeDetails());
    }
}
