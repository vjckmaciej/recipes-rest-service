package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recipes.domain.Recipe;
import recipes.dto.RecipeDTO;
import recipes.domain.IDObject;
import recipes.service.RecipeService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RecipeController {
    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/recipe/new")
    public IDObject saveRecipe(@RequestBody RecipeDTO recipeDTO) {
        return  recipeService.saveRecipe(recipeDTO);
    }

    @GetMapping("/recipe/{id}")
    public RecipeDTO getRecipe(@PathVariable int id) {
        return recipeService.getRecipe(id);
    }

    @GetMapping("/recipe/allRecipes")
    public Map<Integer, Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

}
