package yang.springframework.recipe.services;


import yang.springframework.recipe.commands.RecipeCommand;
import yang.springframework.recipe.models.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
