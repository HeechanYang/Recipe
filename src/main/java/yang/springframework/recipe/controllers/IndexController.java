package yang.springframework.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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
    public String index(){
        Set<Recipe> recipeSet = recipeService.getRecipes();

        for(Recipe recipe : recipeSet){
            System.out.println(recipe.getId());
        }

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("recipes",recipeService.getRecipes());
        return "index";
    }
}
