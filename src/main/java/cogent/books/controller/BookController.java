package cogent.books.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cogent.books.dao.BookJPA;
import cogent.books.dao.GenreJPA;
import cogent.books.entities.Book;
import cogent.books.entities.Genre;
import cogent.books.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	BookJPA bRepo;
	@Autowired
	BookService bServe;
	@Autowired
	GenreJPA gRepo;

	@GetMapping("/delete")
	public String deleteBookEntry(@RequestParam("id") int id) {

		Book book = bServe.findById(id);
		//bRepo.deleteById(id);
		bServe.delete(book);
		
		
		
		return "Deleted " + book.getTitle() + " from the repository";
	}
	
	@GetMapping("/findall")
	public List<Book> findAll() {
		List<Book> books = bServe.findAll();
		
		return books;
	}
	
	@GetMapping("/findbytitle")
	public Book findByTitle(@RequestParam("title") String title) {
		Book book = bServe.findByTitle(title);
		
		return book;
	}
	
	@GetMapping("/findbygenrename")
	public List<Book> findByGenreName(@RequestParam("name") String name) {
		List<Book> books = bServe.findByGenre(name);
		
		return books;

	}

	

	@GetMapping("/title")
	public String findTitle(Book book) {
		return book.getTitle();
	}

}
