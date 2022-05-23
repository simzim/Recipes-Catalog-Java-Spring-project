package lt.simzim.recipescatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.simzim.recipescatalog.entities.Component;
import lt.simzim.recipescatalog.repositories.ComponentRepository;

@Service
public class ComponentService {
	
	@Autowired
	ComponentRepository componentRepository;
	
	public Component addComponent(Component component) {
		return componentRepository.save(component);
	}
	
	public List<Component> getComponents(){
		return componentRepository.findAll();
	}
	
	public Component getComponent(Integer id) {
		return componentRepository.getById(id);
	}
	
	public void deleteComponent (Integer id) {
		componentRepository.deleteById(id);
	}
	
	
}
