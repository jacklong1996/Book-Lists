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

import cogent.books.bookapis.BooksSample;
import cogent.books.dao.BookJPA;
import cogent.books.entities.Book;
import cogent.books.services.BookService;

@RestController
@CrossOrigin()
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
			System.out.print("Added: " + book.getTitle() +" by: ");
	        book.getAuthors().forEach(n -> n.print()); 
	        System.out.println(" to the db.");
		}
		
		return "Added books to the database.";
	}
	
	@GetMapping("/searchbytitle")
	public List<Book> searchByTitle(@RequestParam("search") String search) {
		return bs.findBooks("author", search);
	}
	
}
