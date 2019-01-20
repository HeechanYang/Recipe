package yang.springframework.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yang.springframework.recipe.commands.RecipeCommand;
import yang.springframework.recipe.services.RecipeService;

@Controller
@Slf4j
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));

        return "recipe/show";
    }

    @GetMapping("/recipe/new")
    public String insertRecipePage(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));

        return "recipe/recipeform";
    }

    @PostMapping("/recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @DeleteMapping("/recipe/{id}")
    public String deleteRecipeById(@PathVariable("id") Long id) {
        recipeService.deleteById(id);

        return "redirect:/";
    }
}