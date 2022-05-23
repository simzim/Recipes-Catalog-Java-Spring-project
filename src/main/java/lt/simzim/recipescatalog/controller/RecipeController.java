package lt.simzim.recipescatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lt.simzim.recipescatalog.entities.Recipe;
import lt.simzim.recipescatalog.services.FileStorageService;
import lt.simzim.recipescatalog.services.RecipeService;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
	
	@Autowired
	RecipeService recipeService;
	
	@Autowired
	FileStorageService storageService;
	
	
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
							@RequestParam("serving") Integer serving,
							@RequestParam("file") MultipartFile file) {
		
		Recipe r = new Recipe(name, description, duration, serving, file.getOriginalFilename());
		r = recipeService.addRecipe(r);
		
		storageService.store(file, r.getId().toString());
		
		return "redirect:/recipe/";
	}
	
	
	@GetMapping("/img/{filename}")
	@ResponseBody
	public ResponseEntity<Resource> getImage(@PathVariable() String filename) {
		Resource file = storageService.loadFile(filename);
		
		if (file==null) {
			return ResponseEntity.status(404).body(null);
		}
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_TYPE , "image/jpeg") 
				//.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+filename+"\"")
				.body(file);
				//.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; ");
		
	}
	
	
	
	@GetMapping("/{filename}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable() String filename) {
		Resource file = storageService.loadFile(filename);
		
		if (file==null) {
			return ResponseEntity.status(404).body(null);
		}
		return ResponseEntity
				.ok()
				//.header(HttpHeaders.CONTENT_TYPE, storageService.getContentType(filename)) 
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+filename+"\"")
				.body(file);
				//.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; ");
	}
	
	
	@GetMapping("/detail/{id}")
	public String detailRecipe(@PathVariable("id") Integer id, 
								Model model) {
		model.addAttribute("recipe", recipeService.getRecipe(id));
		
		return "recipe_detail";
	}
	
	
	
	@GetMapping("/update/{id}")
	public String updateRecipe(@PathVariable("id") Integer id, 
								Model model) {
		model.addAttribute("recipe", recipeService.getRecipe(id));
		
		return "recipe_update";
	}

	@PostMapping("/update/{id}")
	public String recipeUpdate (@PathVariable("id") Integer id, 
								@ModelAttribute Recipe r,
								@RequestParam("file") MultipartFile file) {
		
		
		r.setFileName(file.getOriginalFilename());
		
		storageService.store(file, r.getId().toString());
		System.out.println("Failo pavadinimas: " + file);
		recipeService.updateRecipe(r);
		return "redirect:/recipe/";
	}
	
	@GetMapping ("/delete/{id}")
	public String recipeDelete(@PathVariable("id") Integer id) {
		recipeService.deleteRecipe(id);
		return "redirect:/recipe/";
		
	}
}
