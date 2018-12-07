package yang.springframework.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import yang.springframework.recipe.models.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
