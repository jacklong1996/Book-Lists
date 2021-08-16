package cogent.books.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import cogent.books.entities.Book;

@CrossOrigin("http://localhost:4200")
public interface BookJPA extends JpaRepository<Book, Integer>{
	public Optional<Book> findByTitle(String title);
}
