package lt.simzim.recipescatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lt.simzim.recipescatalog.entities.Product;
import lt.simzim.recipescatalog.services.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("products", productService.getProducts());
		return "product_list";
	}
	
	@GetMapping("/new")
	public String productNew(Model model) {
		return "product_new";
	}
	
	@PostMapping("/new")
	public String addProduct(
			@ModelAttribute Product product, 
			Model model) {

		productService.addProduct(product);
		return "redirect:/product/";
	}
	
	@GetMapping("/update/{id}")
	public String productNew(
			@PathVariable("id") Integer id, 
			Model model) {
		model.addAttribute("product", productService.getProduct(id));
		return "product_update";
	}

	@PostMapping("/update/{id}")
	public String productUpdate(
			@ModelAttribute Product product,
			@PathVariable("id") Integer id
			) {
		productService.updateProduct(product);
		return "redirect:/product/";
	}

	@GetMapping("/delete/{id}")
	public String productDelete(@PathVariable("id") Integer id) {
		productService.deleteProduct(id);
		return "redirect:/product/";
	}
	
}
