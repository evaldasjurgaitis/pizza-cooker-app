package baltic.amadeus.pizzacooker.controller;

import baltic.amadeus.pizzacooker.dto.RecipeDetails;
import baltic.amadeus.pizzacooker.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "/recipes/")
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @PostMapping
    public ResponseEntity<RecipeDetails> save(@RequestBody RecipeDetails recipeDetails) {
        return status(HttpStatus.OK).body(recipeService.save(recipeDetails));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<RecipeDetails> get(@PathVariable("id") Integer id) {
        return status(HttpStatus.OK).body(recipeService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<RecipeDetails>> list() {
        return status(HttpStatus.OK).body(recipeService.list());
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Integer id) {
        recipeService.delete(id);
    }
}
