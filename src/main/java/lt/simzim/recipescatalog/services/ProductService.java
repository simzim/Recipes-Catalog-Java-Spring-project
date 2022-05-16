package lt.simzim.recipescatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.simzim.recipescatalog.entities.Product;
import lt.simzim.recipescatalog.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	public Product addProduct (Product product) {
		return productRepository.save(product);
	}
	
	public List<Product> getProducts(){
		return productRepository.findAll();
	}
	
	public Product updateProduct(Product product) {
		Product old = productRepository.getById(product.getId());
		old.setName(product.getName());
		old.setPrice(product.getPrice());
		old.setUnit(product.getUnit());
		old.setWeight(product.getWeight());
		productRepository.save(old);
		return old;
	}
	
	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
	}
	
	public Product getProduct(Integer id) {
		return productRepository.getById(id);
	}
	
	
}
