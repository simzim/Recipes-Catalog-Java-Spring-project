package lt.simzim.recipescatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.simzim.recipescatalog.entities.Component;

public interface ComponentRepository extends JpaRepository<Component, Integer>{

}
