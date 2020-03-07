package baltic.amadeus.pizzacooker.unit.dto;

import baltic.amadeus.pizzacooker.dto.MessageType;
import baltic.amadeus.pizzacooker.dto.PizzaDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class PizzaDetailsTest {

    @Test
    public void newPizza() {
        PizzaDetails pizzaDetails = new PizzaDetails(null, MessageType.NO_ENOUGH_PRODUCTS);
        assertEquals(MessageType.NO_ENOUGH_PRODUCTS, pizzaDetails.getMessageType());
        assertEquals(MessageType.NO_ENOUGH_PRODUCTS.getMessage(), pizzaDetails.getMessageType().getMessage());
        assertNull( pizzaDetails.getPizza());
    }
}
