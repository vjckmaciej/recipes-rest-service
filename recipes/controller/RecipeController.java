package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recipes.domain.Recipe;
import recipes.dto.RecipeDTO;
import recipes.service.RecipeService;

@RestController
@RequestMapping("/api")
public class RecipeController {
    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/recipe")
    public void saveRecipe(@RequestBody RecipeDTO recipeDTO) {
        recipeService.saveRecipe(recipeDTO);
    }

    @GetMapping("/recipe")
    public Recipe getRecipe() {
        return recipeService.getRecipe();
    }

}
