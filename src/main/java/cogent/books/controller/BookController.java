package cogent.books.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.json.Json;
import com.google.gson.Gson;

import cogent.books.dao.BookJPA;
import cogent.books.entities.Book;
import cogent.books.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	BookJPA bRepo;
	@Autowired
	BookService bServe;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/delete")
	public String deleteBookEntry(@RequestParam("id") int id) {
		Book book = bServe.findById(id);
		//bRepo.deleteById(id);
		bServe.delete(book);
		
		return "Deleted " + book.getTitle() + " from the repository";
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/findall")
	public List<Book> findAll() {
		List<Book> books = bServe.findAll();
		
		return books;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/findbytitle")
	public Book findByTitle(@RequestParam("title") String title) {
		Book book = bServe.findByTitle(title);
		
		return book;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/findbygenrename")
	public List<Book> findByGenreName(@RequestParam("name") String name) {
		List<Book> books = bServe.findByGenre(name);
		//Json;
		return books;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/findallpages")
	public List<Book> findAllPages() {
		List<Book> books = bServe.findAll();
		
		return books.subList(0, 20);
	}
}
