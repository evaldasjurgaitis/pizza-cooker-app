package baltic.amadeus.pizzacooker.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "recipes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Recipe implements Serializable {
    @Id
    @SequenceGenerator(name = "pk_sequence_recipe", sequenceName = "recipe_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence_recipe")
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ElementCollection
    private Set<Product> products;

    public Recipe() {
    }

    public Recipe(Integer id, String name, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Recipe(String name) {
        this.name = name;
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
