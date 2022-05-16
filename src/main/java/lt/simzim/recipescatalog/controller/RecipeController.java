package lt.simzim.recipescatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lt.simzim.recipescatalog.entities.Recipe;
import lt.simzim.recipescatalog.services.RecipeService;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
	@Autowired
	RecipeService recipeService;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("recipes", recipeService.getRecipes());
		return "recipe_list";
	}
	
	@GetMapping("/new")
	public String recipeNew(Model model) {
		return "recipe_new";
	}
	
	@PostMapping("/new")
	public String addRecipe(@RequestParam("name") String name,
							@RequestParam("description") String description, 
							@RequestParam("duration")Integer duration, 
							@RequestParam("serving") Integer serving) {
		
		Recipe r = new Recipe(name, description, duration, serving);
		recipeService.addRecipe(r);
		return "redirect:/recipe/";
	}
	
	@GetMapping("/update/{id}")
	public String updateRecipe(@PathVariable("id") Integer id, 
								Model model) {
		model.addAttribute("recipe", recipeService.getRecipe(id));
		
		return "recipe_update";
	}

	@PostMapping("/update/{id}")
	public String recipeUpdate (@PathVariable("id") Integer id, @ModelAttribute Recipe r) {
		recipeService.updateRecipe(r);
		return "redirect:/recipe/";
	}
	
	@GetMapping ("/delete/{id}")
	public String recipeDelete(@PathVariable("id") Integer id) {
		recipeService.deleteRecipe(id);
		return "redirect:/recipe/";
		
	}
}
