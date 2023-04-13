package recipes.service;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.domain.IDObject;
import recipes.domain.Recipe;
import recipes.domain.User;
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

    public IDObject saveRecipe(RecipeDTO recipeDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //recipeDTO.setUser((User) auth.getPrincipal());
        Recipe recipe = toRecipeFromDTO(recipeDTO);
        recipe.setUser((User) auth.getPrincipal());
        if (recipe.getUser() != null) {
            recipeRepository.save(recipe);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user is null");
        }

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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (recipeRepository.existsById(id)) {
            if (recipeRepository.findById(id).get().getUser() == null) {
                recipeRepository.deleteById(id);
                throw new ResponseStatusException(HttpStatus.NO_CONTENT);
            } else if (recipeRepository.findById(id).get().getUser().getEmail().equals(auth.getName())) {
                recipeRepository.deleteById(id);
                throw new ResponseStatusException(HttpStatus.NO_CONTENT);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (recipeRepository.existsById(id) && recipeRepository.findById(id).get().getUser() != null
//                && recipeRepository.findById(id).get().getUser().getEmail().equals(auth.getName())) {
//            recipeRepository.deleteById(id);
//            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
//        } else {
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
//        }
    }

    public void updateRecipe(RecipeDTO recipeDTO, Long id) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (recipeRepository.existsById(id) && recipeRepository.findById(id).get().getUser() != null
//                && recipeRepository.findById(id).get().getUser().getEmail().equals(auth.getName())) {
//            Recipe updatedRecipe = toRecipeFromDTO(recipeDTO);
//            updatedRecipe.setId(id);
//            recipeRepository.save(updatedRecipe); // updates database with new version of recipe with given ID
//            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
//        } else {
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
//        }


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (recipeRepository.existsById(id)) {
            if (recipeRepository.findById(id).get().getUser() == null) {
                Recipe updatedRecipe = toRecipeFromDTO(recipeDTO);
                updatedRecipe.setId(id);
                recipeRepository.save(updatedRecipe); // updates database with new version of recipe with given ID
                throw new ResponseStatusException(HttpStatus.NO_CONTENT);
            } else if (recipeRepository.findById(id).get().getUser().getEmail().equals(auth.getName())) {
                Recipe updatedRecipe = toRecipeFromDTO(recipeDTO);
                updatedRecipe.setId(id);
                recipeRepository.save(updatedRecipe); // updates database with new version of recipe with given ID
                throw new ResponseStatusException(HttpStatus.NO_CONTENT);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<Recipe> getAllRecipes(String category, String name) {
        if ((category != null && name != null) || (category == null && name == null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else if (category != null) {
            return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
        } else {
            return recipeRepository.findByNameContainingIgnoreCaseOrderByDateDesc(name);
        }
    }
}
