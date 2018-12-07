package yang.springframework.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import yang.springframework.recipe.models.Recipe;
import yang.springframework.recipe.services.RecipeService;

import java.util.Set;

@Controller
@RequestMapping("/")
public class IndexController {
    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/","","index","index.html"})
    public String index(Model model){
        Set<Recipe> recipeSet = recipeService.getRecipes();

        for(Recipe recipe : recipeSet){
            System.out.println(recipe.getId());
        }

        model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }
}
