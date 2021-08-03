package cogent.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cogent.books.bookapis.BooksSample;
import cogent.books.services.BookService;

@RestController
@RequestMapping("/gbooks")
public class GoogleBookController {
	@Autowired
	//GoogleBooksService bs;
	BooksSample bs;
	
	@PostMapping("/add")
	public String addBooks(@RequestBody ObjectNode json) {
		//return bs.addBook(type, query);
		return bs.findBooks(json.get("type").asText(), json.get("search").asText());
		
		//return "";
	}
}
