package cogent.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cogent.books.bookapis.BooksSample;
import cogent.books.dao.BookJPA;
import cogent.books.entities.Book;
import cogent.books.services.BookService;

@RestController
@RequestMapping("/gbooks")
public class GoogleBookController {
	@Autowired
	//GoogleBooksService bs;
	BooksSample bs;
	@Autowired
	BookService bServ;
	
	
	@PostMapping("/add")
	public String addBooks(@RequestBody ObjectNode json) {
		//return bs.addBook(type, query);
		List<Book> books = bs.findBooks(json.get("type").asText(), json.get("search").asText());
		
		for (Book book : books) {
			bServ.save(book);
		}
		
		return "Added books to the database.";
	}
	
}
