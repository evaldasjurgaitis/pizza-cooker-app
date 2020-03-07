package baltic.amadeus.pizzacooker.dto;

public class Pizza {
    private PizzaType type;
    private RecipeDetails recipeDetails;

    public Pizza() {
    }

    public Pizza(PizzaType type, RecipeDetails recipeDetails) {
        this.type = type;
        this.recipeDetails = recipeDetails;
    }

    public PizzaType getType() {
        return type;
    }

    public void setType(PizzaType type) {
        this.type = type;
    }

    public RecipeDetails getRecipeDetails() {
        return recipeDetails;
    }

    public void setRecipeDetails(RecipeDetails recipeDetails) {
        this.recipeDetails = recipeDetails;
    }
}


