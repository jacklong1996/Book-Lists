package cogent.books.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cogent.books.entities.Book;

public interface BookJPA extends JpaRepository<Book, Integer>{
	public Book findByTitle(String title);
	
	public List<Book> findAll(Book books);
	
}
