package lt.simzim.recipescatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.simzim.recipescatalog.entities.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

}
