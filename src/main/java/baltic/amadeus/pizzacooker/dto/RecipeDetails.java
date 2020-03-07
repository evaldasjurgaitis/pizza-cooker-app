package baltic.amadeus.pizzacooker.dto;

import java.util.Set;

public class RecipeDetails {
    private Integer id;
    private String name;
    private Set<ProductDetails> productsDetails;

    public RecipeDetails() {
    }

    public RecipeDetails(Integer id, String name, Set<ProductDetails> productsDetails) {
        this.id = id;
        this.name = name;
        this.productsDetails = productsDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductDetails> getProductsDetails() {
        return productsDetails;
    }

    public void setProductsDetails(Set<ProductDetails> productsDetails) {
        this.productsDetails = productsDetails;
    }
}
