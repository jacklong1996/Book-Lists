package cogent.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import cogent.books.entities.Book;
import cogent.books.entities.User;
import cogent.books.services.UserService;

@RestController
@CrossOrigin()
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
	public String addWant(@RequestParam("uid") int uid, @RequestParam("bid") int bid) {
		return us.addWant(uid, bid);
	}
	
	@GetMapping("/addRead")
	public String addRead(@RequestParam("uid") int uid, @RequestParam("bid") int bid) {
		return us.addRead(uid, bid);
	}
	
	@GetMapping("/findwant")
	public List<Book> findWant(@RequestParam("uid") int uid) { 
		return us.findById(uid).getWant();
	}
	
	@GetMapping("/findread")
	public List<Book> findRead(@RequestParam("uid") int uid) { 
		return us.findById(uid).getRead();
	}
	
	@GetMapping(value = "/addtowant")
	public String addToWant(@RequestParam("uid") int uid, @RequestParam("bid") int bid) {
		Gson g = new Gson();
		return g.toJson(us.addToWant(uid, bid));
		//new JsonObject().
		//return new JsonObject().add("text", us.addToWant(uid, bid));
		 
		//return ;
	}
	
	@GetMapping("/addtoread")
	public String addToRead(@RequestParam("uid") int uid, @RequestParam("bid") int bid) {
		Gson g = new Gson();
		return g.toJson(us.addToRead(uid, bid));
	}
}
