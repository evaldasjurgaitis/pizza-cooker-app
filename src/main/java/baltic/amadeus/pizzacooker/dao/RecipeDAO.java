package baltic.amadeus.pizzacooker.dao;

import baltic.amadeus.pizzacooker.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeDAO extends JpaRepository<Recipe, Integer> {
}
