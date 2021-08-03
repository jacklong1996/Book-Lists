package cogent.books.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cogent.books.dao.BookJPA;
import cogent.books.entities.Book;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	BookJPA bRepo;
	
	@GetMapping("/delete")
	public String deleteBookEntry(@RequestParam("id") int id) {
		Optional<Book> book = bRepo.findById(id);
		//bRepo.deleteById(id);
		bRepo.delete(book.get());
		
		
		
		return "Deleted " + book.get().getTitle() + " from the repository";
	}
}
