package yang.springframework.recipe.services;


import yang.springframework.recipe.commands.RecipeCommand;
import yang.springframework.recipe.models.Recipe;

import java.util.Set;

public interface RecipeService {
    public Set<Recipe> getRecipes();

    public Recipe findById(Long l);

    public RecipeCommand saveRecipeCommand(RecipeCommand command);

    public void deleteById(Long idToDelete);
}
