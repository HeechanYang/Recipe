package yang.springframework.recipe.services.impl;

import org.springframework.stereotype.Service;
import yang.springframework.recipe.models.Recipe;
import yang.springframework.recipe.repositories.RecipeRepository;
import yang.springframework.recipe.services.RecipeService;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }
}
