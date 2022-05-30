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

import lt.simzim.recipescatalog.entities.Component;
import lt.simzim.recipescatalog.services.ComponentService;
import lt.simzim.recipescatalog.services.ProductService;
import lt.simzim.recipescatalog.services.RecipeService;

@Controller
@RequestMapping("/component")
public class ComponentController {
	
	@Autowired
	ComponentService componentService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	RecipeService recipeService;
//	
//
//	@GetMapping("/detail/{id}")
//	public String getComponents(@PathVariable("id") Integer recipe_id, 
//								Model model) {
//		model.addAttribute("components", componentService.findAllByRecipe(recipe_id));
//		
//		return "/detail/{id}";
//	}
//	
//	
//	
//	
	@GetMapping("/new/{id}")
	public String newComponent(@PathVariable("id") Integer id, 
								Model model) {
	model.addAttribute("component", new Component());
	model.addAttribute("recipe", recipeService.getRecipe(id));
	System.out.println("recepto ID " + recipeService.getRecipe(id));  
	model.addAttribute("products", productService.getProducts());
		return "component_new";
	}
	
	@PostMapping("/new/{id}")
	public String storeComponent(
			@ModelAttribute Component component,
			@RequestParam("recipeId") Integer recipeId, 
			@RequestParam("productId") Integer productId
			) {
		
		component.setRecipe(recipeService.getRecipe(recipeId));
		component.setProduct(productService.getProduct(productId));
		componentService.addComponent(component);
		
		return "redirect:/component/new/{id}";
	}
	
	
}
