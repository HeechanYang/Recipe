package yang.springframework.recipe.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yang.springframework.recipe.commands.IngredientCommand;
import yang.springframework.recipe.converters.IngredientCommandToIngredient;
import yang.springframework.recipe.converters.IngredientToIngredientCommand;
import yang.springframework.recipe.models.Ingredient;
import yang.springframework.recipe.models.Recipe;
import yang.springframework.recipe.repositories.RecipeRepository;
import yang.springframework.recipe.repositories.UnitOfMeasureRepository;
import yang.springframework.recipe.services.IngredientService;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand
            , IngredientCommandToIngredient ingredientCommandToIngredient
            , RecipeRepository recipeRepository
            , UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            log.error("Recipe is not found Id : " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientToIngredientCommand::convert).findFirst();

        if (!ingredientCommandOptional.isPresent()) {
            log.error("Ingredient is not found Id : " + ingredientId);
        }

        return ingredientCommandOptional.get();
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if (!recipeOptional.isPresent()) {
            log.error("Recipe not found id : " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe.getIngredients()
                    .stream().filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                Ingredient ingredient = ingredientOptional.get();
                ingredient.setDescription(command.getDescription());
                ingredient.setAmount(command.getAmount());
                ingredient.setUom(unitOfMeasureRepository
                        .findById(ingredient.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND")));
            } else {
                recipe.addIngredient(ingredientCommandToIngredient.convert(command));
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            return ingredientToIngredientCommand.convert(savedRecipe.getIngredients()
                    .stream().filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst().get());
        }
    }

    @Override
    public void deleteById(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe is not found id : " + recipeId);
        } else {
            Recipe savedRecipe = recipeOptional.get();
            Optional<Ingredient> ingredientOptional = savedRecipe.getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientId))
                    .findFirst();
            if(!ingredientOptional.isPresent()){
                throw new RuntimeException("Ingredient is not found id : " + ingredientId);
            }else{
                Ingredient ingredientToDelete = ingredientOptional.get();
                savedRecipe.getIngredients().remove(ingredientToDelete);
                recipeRepository.save(savedRecipe);
            }
        }

    }
}
