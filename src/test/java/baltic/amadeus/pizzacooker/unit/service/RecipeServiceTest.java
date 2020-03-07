package baltic.amadeus.pizzacooker.unit.service;

import baltic.amadeus.pizzacooker.dao.RecipeDAO;
import baltic.amadeus.pizzacooker.dto.ProductDetails;
import baltic.amadeus.pizzacooker.dto.RecipeDetails;
import baltic.amadeus.pizzacooker.entity.Product;
import baltic.amadeus.pizzacooker.entity.Recipe;
import baltic.amadeus.pizzacooker.service.RecipeServiceImpl;
import baltic.amadeus.pizzacooker.component.RecipeComponent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {

    @Mock
    protected RecipeDAO recipeDAO;

    @Mock
    RecipeComponent recipeComponent;

    @InjectMocks
    protected RecipeServiceImpl recipeService;

    private RecipeDetails recipeDetails;

    private Recipe recipe;

    private final Integer RECIPE_ID = 1;


    @Before
    public void setUp() {
        recipeService = Mockito.spy(recipeService);
        Set<ProductDetails> recipeProductsDetails = new HashSet<>();
        recipeProductsDetails.add(new ProductDetails("dough", 1));
        recipeProductsDetails.add(new ProductDetails("cheese", 1));
        recipeProductsDetails.add(new ProductDetails("salemi", 1));
        recipeDetails = new RecipeDetails(1,"Margarita", recipeProductsDetails);


        Set<Product> recipeProducts = new HashSet<>();
        recipeProducts.add(new Product("dough", 1));
        recipeProducts.add(new Product("cheese", 1));
        recipeProducts.add(new Product("salemi", 1));
        recipe = new Recipe(1,"Margarita", recipeProducts);
    }

    @Test
    public void Should_Save_Recipe() {
        when(recipeComponent.convertToEntity(recipeDetails)).thenReturn(recipe);
        recipeService.save(recipeDetails);
        Mockito.verify(recipeDAO, times(1)).save(any());
    }

    @Test
    public void Should_Get_Recipe() {
        Optional<Recipe> expectedReceipe = Optional.of(recipe);
        when(recipeDAO.findById(recipe.getId())).thenReturn(expectedReceipe);
        when(recipeComponent.convertToDto(recipe)).thenReturn(recipeDetails);
        RecipeDetails actualRecipeDetails = recipeService.get(RECIPE_ID);
        Mockito.verify(recipeDAO, times(1)).findById(any());
        Assert.assertEquals(expectedReceipe.get().getId(), actualRecipeDetails.getId());
        Assert.assertEquals(expectedReceipe.get().getName(), actualRecipeDetails.getName());
        Assert.assertNotNull(actualRecipeDetails.getProductsDetails());
    }


    @Test
    public void Should_Get_List_Recipe() {
        List<Recipe> expectedList = mock(List.class);
        when(recipeDAO.findAll()).thenReturn(expectedList);
        List<RecipeDetails> actualList = recipeService.list();
        Mockito.verify(recipeDAO, times(1)).findAll();
        Assert.assertEquals(expectedList.size(), actualList.size());
    }

    @Test
    public void Should_Delete_Recipe() {
        when(recipeDAO.save(recipe)).thenReturn(recipe);
        when(recipeComponent.convertToEntity(recipeDetails)).thenReturn(recipe);
        when(recipeComponent.convertToDto(recipe)).thenReturn(recipeDetails);
        recipeService.save(recipeDetails);
        recipeService.delete(RECIPE_ID);
        Mockito.verify(recipeDAO, times(1)).deleteById(any());
    }

    @Test
    public void Should_Get_ReceipeProductMap() {
        Map<String, Integer> expectedProductsMap = new HashMap<>();
        expectedProductsMap.put("dough", 1);
        expectedProductsMap.put("cheese", 1);
        expectedProductsMap.put("salemi", 1);
        when(recipeDAO.findById(RECIPE_ID)).thenReturn(Optional.of(recipe));
        when(recipeComponent.convertToDto(recipe)).thenReturn(recipeDetails);
        Map<String, Integer> actualProductsMap = recipeService.getRecipeProductsMap(RECIPE_ID);

        Assert.assertEquals(expectedProductsMap.get("dough"), actualProductsMap.get("dough"));
        Assert.assertEquals(expectedProductsMap.get("cheese"), actualProductsMap.get("cheese"));
        Assert.assertEquals(expectedProductsMap.get("salemi"), actualProductsMap.get("salemi"));
    }
}
