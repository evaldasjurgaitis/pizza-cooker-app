package baltic.amadeus.pizzacooker.unit.service;

import baltic.amadeus.pizzacooker.dto.*;
import baltic.amadeus.pizzacooker.service.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@TestPropertySource(properties = {"cooker-machine.cook-count.limit=20"})
public class PizzaServiceTest {

    @Mock
    protected CookerMachineService cookerMachineService;

    @Mock
    protected RecipeService recipeService;

    @InjectMocks
    protected PizzaServiceImpl pizzaService;

    @Before
    public void setUp() {
        pizzaService = Mockito.spy(pizzaService);    }

    @Test
    public void shouldGetPizza() {
        ReflectionTestUtils.setField(pizzaService, "cookCountLimit", 20);
        RecipeDetails recipeDetails = new RecipeDetails(3, "Venecija", null);
        CookerMachineDetails cookerMachineDetails = new CookerMachineDetails(1, "Alfa", 0, null, true);

        Map<String, Integer> cookerMachineProductsMap = new HashMap<>();
        cookerMachineProductsMap.put("dough", 10);
        cookerMachineProductsMap.put("cheese", 10);
        cookerMachineProductsMap.put("salemi", 10);
        cookerMachineProductsMap.put("mushrooms", 10);
        cookerMachineProductsMap.put("tomatoes", 10);
        cookerMachineProductsMap.put("sauce", 10);

        Map<String, Integer> recipeProductsMap = new HashMap<>();
        recipeProductsMap.put("dough", 1);
        recipeProductsMap.put("cheese", 1);
        recipeProductsMap.put("cheese", 1);
        recipeProductsMap.put("tomatoes", 1);


        when(cookerMachineService.get(cookerMachineDetails.getId())).thenReturn(cookerMachineDetails);
        when(recipeService.get(recipeDetails.getId())).thenReturn(recipeDetails);
        when(cookerMachineService.getCookerMachineProductsMap(cookerMachineDetails.getId())).thenReturn(cookerMachineProductsMap);
        when(recipeService.getRecipeProductsMap(recipeDetails.getId())).thenReturn(recipeProductsMap);

        PizzaDetails pizzaDetails = pizzaService.getPizza(cookerMachineDetails.getId(), recipeDetails.getId(), PizzaType.SMALL.getValue());
        Assert.assertNotNull(pizzaDetails.getPizza());
        Assert.assertEquals(MessageType.PIZZA_PRODUCED, pizzaDetails.getMessageType());
    }

    @Test
    public void shouldGetOfferPizza() {
        ReflectionTestUtils.setField(pizzaService, "cookCountLimit", 20);
        CookerMachineDetails cookerMachineDetails = new CookerMachineDetails(1, "Alfa", 0, null,true
        );
        List<RecipeDetails> recipeDetailsList = new ArrayList<>();
        RecipeDetails recipeDetails1 = new RecipeDetails(1, "Venecija", null);
        RecipeDetails recipeDetails2 = new RecipeDetails(2, "Student", null);
        recipeDetailsList.add(recipeDetails1);
        recipeDetailsList.add(recipeDetails2);

        Map<String, Integer> cookerMachineProductsMap = new HashMap<>();
        cookerMachineProductsMap.put("dough", 10);
        cookerMachineProductsMap.put("cheese", 10);
        cookerMachineProductsMap.put("salemi", 10);

        Map<String, Integer> recipeProductsMap1 = new HashMap<>();
        recipeProductsMap1.put("dough", 1);
        recipeProductsMap1.put("cheese", 1);
        recipeProductsMap1.put("cheese", 1);
        recipeProductsMap1.put("tomatoes", 1);
        recipeProductsMap1.put("salemi", 1);


        Map<String, Integer> recipeProductsMap2 = new HashMap<>();
        recipeProductsMap2.put("dough", 1);
        recipeProductsMap2.put("cheese", 1);
        recipeProductsMap2.put("salemi", 1);

        when(cookerMachineService.get(cookerMachineDetails.getId())).thenReturn(cookerMachineDetails);
        when(cookerMachineService.getCookerMachineProductsMap(any())).thenReturn(cookerMachineProductsMap,cookerMachineProductsMap);
        when(recipeService.getRecipeProductsMap(any())).thenReturn(recipeProductsMap1,recipeProductsMap2);

        when(recipeService.get(recipeDetails2.getId())).thenReturn(recipeDetails2);
        when(recipeService.list()).thenReturn(recipeDetailsList);


        PizzaDetails pizzaDetails = pizzaService.getPizza(cookerMachineDetails.getId(), recipeDetails1.getId(), PizzaType.SMALL.getValue());
        Assert.assertNotNull(pizzaDetails.getPizza());
        Assert.assertEquals(MessageType.OFFER_PIZZA, pizzaDetails.getMessageType());
    }

    @Test
    public void shouldGetNoEnoughProducts() {
        ReflectionTestUtils.setField(pizzaService, "cookCountLimit", 20);

        RecipeDetails recipeDetails = new RecipeDetails(3, "Venecija", null);
        StockDetails stockDetails = new StockDetails(1, "Alfa-stock", null);
        CookerMachineDetails cookerMachineDetails = new CookerMachineDetails(1, "Alfa", 0, stockDetails, true);

        Map<String, Integer> receipeProductsMap = new HashMap<>();
        receipeProductsMap.put("dough", 1);
        receipeProductsMap.put("cheese", 1);
        receipeProductsMap.put("salemi", 1);

        Map<String, Integer> cookerMachineProductsMap = new HashMap<>();
        cookerMachineProductsMap.put("dough", 0);
        cookerMachineProductsMap.put("cheese", 0);
        cookerMachineProductsMap.put("salemi", 0);


        when(cookerMachineService.get(cookerMachineDetails.getId())).thenReturn(cookerMachineDetails);
        when(cookerMachineService.getCookerMachineProductsMap(cookerMachineDetails.getId())).thenReturn(cookerMachineProductsMap);
        when(recipeService.getRecipeProductsMap(recipeDetails.getId())).thenReturn(receipeProductsMap);

        PizzaDetails pizzaDetails = pizzaService.getPizza(cookerMachineDetails.getId(), recipeDetails.getId(), PizzaType.SMALL.getValue());
        Assert.assertNull(pizzaDetails.getPizza());
        Assert.assertEquals(MessageType.NO_ENOUGH_PRODUCTS, pizzaDetails.getMessageType());
    }

    @Test
    public void shouldGetCleanMachine() {
        ReflectionTestUtils.setField(pizzaService, "cookCountLimit", 20);
        RecipeDetails recipeDetails = new RecipeDetails(3, "Venecija", null);
        CookerMachineDetails cookerMachineDetails = new CookerMachineDetails(1, "Alfa", 20, null, false);
        when(cookerMachineService.get(cookerMachineDetails.getId())).thenReturn(cookerMachineDetails);
        PizzaDetails pizzaDetails = pizzaService.getPizza(cookerMachineDetails.getId(), recipeDetails.getId(), PizzaType.SMALL.getValue());
        Assert.assertNull(pizzaDetails.getPizza());
        Assert.assertEquals(MessageType.CLEAN_COOKER_MACHINE, pizzaDetails.getMessageType());
    }
}
