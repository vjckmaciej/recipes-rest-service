package recipes.service;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.domain.IDObject;
import recipes.domain.Recipe;
import recipes.dto.RecipeDTO;
import recipes.repository.RecipeRepository;

import java.util.List;
import java.util.Optional;


@Service
public class RecipeService {
    private final ModelMapper modelMapper;
    private final RecipeRepository recipeRepository;

    public RecipeService(ModelMapper modelMapper, RecipeRepository recipeRepository) {
        this.modelMapper = modelMapper;
        this.recipeRepository = recipeRepository;
    }

    public Recipe toRecipeFromDTO(RecipeDTO recipeDTO) {
        return modelMapper.map(recipeDTO, Recipe.class);
    }

    public RecipeDTO toRecipeDTO(Optional<Recipe> recipe) {
        return modelMapper.map(recipe, RecipeDTO.class);
    }

//    public List<Recipe> getAllRecipes() {
//        return  recipeRepository.findAll();
//    }

    public IDObject saveRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = toRecipeFromDTO(recipeDTO);
        recipeRepository.save(recipe);
        return new IDObject(recipe.getId());
    }

    public Optional<RecipeDTO> getRecipe(Long id) {
        if (recipeRepository.existsById(id)) {
            return Optional.ofNullable(toRecipeDTO(recipeRepository.findById(id)));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteRecipe(Long id) {
        if (recipeRepository.existsById(id)) {
            recipeRepository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void updateRecipe(RecipeDTO recipeDTO, Long id) {
        if (recipeRepository.existsById(id)) {
            Recipe updatedRecipe = toRecipeFromDTO(recipeDTO);
            updatedRecipe.setId(id);
            recipeRepository.save(updatedRecipe); // updates database with new version of recipe with given ID
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    public List<Recipe> getAllRecipes(String category, String name) {
        if ((category != null && name != null) || (category == null && name == null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else if (category != null) {
            //something
            return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
        } else {
            //something
            return recipeRepository.findByNameContainingIgnoreCaseOrderByDateDesc(name);
        }
    }
}
