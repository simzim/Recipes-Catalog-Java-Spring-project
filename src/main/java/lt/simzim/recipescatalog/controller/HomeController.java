package lt.simzim.recipescatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lt.simzim.recipescatalog.services.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService; 
	
	@GetMapping("/")
	public String home() {
		//User user=new User("Simona","admin123","info@simzim.lt","admin");
		//	userService.addUser(user);
		return "home";
	}
}
