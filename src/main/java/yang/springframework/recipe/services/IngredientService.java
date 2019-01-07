package yang.springframework.recipe.services;


import yang.springframework.recipe.commands.IngredientCommand;


public interface IngredientService {
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    public void deleteById(Long recipeId, Long ingredientId);
}
