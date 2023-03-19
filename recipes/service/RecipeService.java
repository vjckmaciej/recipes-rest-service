package recipes.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.domain.Recipe;
import org.modelmapper.ModelMapper;
import recipes.dto.RecipeDTO;
import recipes.domain.IDObject;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeService {
    private final ModelMapper modelMapper;
    private final Map<Integer, Recipe> recipesDatabase = new HashMap<>();

    public RecipeService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Recipe toRecipeFromDTO(RecipeDTO recipeDTO) {
        return modelMapper.map(recipeDTO, Recipe.class);
    }

    public RecipeDTO toRecipeDTO(Recipe recipe) {
        return modelMapper.map(recipe, RecipeDTO.class);
    }

    public Map<Integer, Recipe> getAllRecipes() {
        return recipesDatabase;
    }

    public IDObject saveRecipe(RecipeDTO recipeDTO) {
        IDObject IDObject = new IDObject();
        recipesDatabase.put(IDObject.getId(), toRecipeFromDTO(recipeDTO));
        return IDObject;
    }

    public RecipeDTO getRecipe(int id) {
        if (recipesDatabase.containsKey(id)) {
            return toRecipeDTO(recipesDatabase.get(id));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}
