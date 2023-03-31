package recipes.repository;

import org.springframework.data.repository.CrudRepository;
import recipes.domain.Recipe;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    @Override
    List<Recipe> findAll();
}
