package yang.springframework.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yang.springframework.recipe.commands.IngredientCommand;
import yang.springframework.recipe.services.IngredientService;
import yang.springframework.recipe.services.RecipeService;
import yang.springframework.recipe.services.UnitOfMeasureService;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{recipeId}/ingredient")
    public String showById(@PathVariable("recipeId") Long recipeId, Model model) {
        log.debug("Getting ingredient list for recipe id : " + recipeId);

        model.addAttribute("recipe", recipeService.findById(recipeId));
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable("recipeId") Long recipeId
            , @PathVariable("id") Long ingredientId, Model model) {

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable("recipeId") Long recipeId
            , @PathVariable("id") Long ingredientId, Model model) {
        log.debug("Getting Ingredient" + recipeId);

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));

        return "/recipe/ingredient/show";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdateIngredient(@ModelAttribute IngredientCommand command) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient";
    }

    @DeleteMapping("/recipe/{recipeId}/ingredient/{ingredientId}")
    public String deleteIngredientById(@PathVariable("recipeId") Long recipeId
            , @PathVariable("ingredientId") Long ingredientId, Model model) {
        ingredientService.deleteById(recipeId, ingredientId);

        return "redirect:/recipe/" + recipeId + "/ingredient";
    }
}
