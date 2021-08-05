package cogent.books.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cogent.books.entities.Book;

public interface BookJPA extends JpaRepository<Book, Integer>{
	public Optional<Book> findByTitle(String title);
}
