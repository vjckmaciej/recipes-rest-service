package recipes.service;

import org.springframework.stereotype.Service;
import recipes.domain.Recipe;
import org.modelmapper.ModelMapper;
import recipes.dto.RecipeDTO;

@Service
public class RecipeService {
    private final ModelMapper modelMapper;
    private Recipe recipe;

    public RecipeService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Recipe toRecipeFromDTO(RecipeDTO recipeDTO) {
        return modelMapper.map(recipeDTO, Recipe.class);
    }

    public RecipeDTO toRecipeDTO(Recipe recipe) {
        return modelMapper.map(recipe, RecipeDTO.class);
    }

    public void saveRecipe(RecipeDTO recipeDTO) {
        recipe = toRecipeFromDTO(recipeDTO);
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
