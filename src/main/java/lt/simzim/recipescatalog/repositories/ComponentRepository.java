package lt.simzim.recipescatalog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.simzim.recipescatalog.entities.Component;

public interface ComponentRepository extends JpaRepository<Component, Integer>{
	List<Component> findAllByRecipe(Integer recipe_id);
}
