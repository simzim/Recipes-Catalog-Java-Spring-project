package lt.simzim.recipescatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.simzim.recipescatalog.entities.Recipe;
import lt.simzim.recipescatalog.repositories.RecipeRepository;

@Service
public class RecipeService {
	
	@Autowired
	RecipeRepository recipeRepository;
	
	public Recipe addRecipe(Recipe recipe) {
		return recipeRepository.save(recipe);
	}
	
	public List<Recipe> getRecipes(){
		return recipeRepository.findAll();
	}
	
	public Recipe updateRecipe(Recipe recipe) {
		Recipe old = recipeRepository.getById(recipe.getId());
		old.setName(recipe.getName());
		old.setDescription(recipe.getDescription());
		old.setDuration(recipe.getDuration());
		old.setServing(recipe.getServing());
		old.setFileName(recipe.getFileName());
		recipeRepository.save(old);
		return old;
	}
	
	public void deleteRecipe(Integer id) {
		recipeRepository.deleteById(id);
	}
	
	public Recipe getRecipe(Integer id) {
		return recipeRepository.getById(id);
	}
	

}
