package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recipes.domain.Recipe;
import recipes.dto.RecipeDTO;
import recipes.domain.IDObject;
import recipes.service.RecipeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class RecipeController {
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/api/recipe/new")
    public IDObject saveRecipe(@Valid @RequestBody RecipeDTO recipeDTO) {
        return  recipeService.saveRecipe(recipeDTO);
    }

    @GetMapping("/api/recipe/{id}")
    public Optional<RecipeDTO> getRecipe(@PathVariable Long id) {
        return recipeService.getRecipe(id);
    }

    @DeleteMapping("/api/recipe/{id}")
    public void deleteRecipe(@PathVariable Long id) { recipeService.deleteRecipe(id);}

    @GetMapping("/api/recipe/allRecipes")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @PutMapping("/api/recipe/{id}")
    public void updateRecipe(@PathVariable Long id, @Valid @RequestBody RecipeDTO recipeDTO) {
        recipeService.updateRecipe(recipeDTO, id);
    }

}
