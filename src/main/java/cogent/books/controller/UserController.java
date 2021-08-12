package cogent.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cogent.books.entities.Book;
import cogent.books.entities.User;
import cogent.books.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService us;
	
	@PostMapping("/addUser")
	public String addUser(@RequestBody ObjectNode json) {
		User user = new User(json.get("username").asText(), 
				json.get("email").asText(),
				json.get("password").asText(),
				json.get("name").asText());
		
		us.addUser(user);
		
		return "Added user.";
	}
	
	@GetMapping("/addWant")
	public String addWant(@RequestParam("id") int uid, @RequestParam("bid") int bid) {
		return us.addWant(uid, bid);
	}
	
	@GetMapping("/addRead")
	public String addRead(@RequestParam("id") int uid, @RequestParam("bid") int bid) {
		return us.addRead(uid, bid);
	}
}
