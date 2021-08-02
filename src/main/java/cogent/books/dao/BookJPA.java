package cogent.books.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cogent.books.entities.Book;

public interface BookJPA extends JpaRepository<Book, Integer>{
	public Book findByTitle(String title);
}
