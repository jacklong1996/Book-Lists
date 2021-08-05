package cogent.books.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cogent.books.entities.Author;

public interface AuthorJPA extends JpaRepository<Author, Integer>{
	public Author findByName(String name);
	
	public Author findByAuthorId(int id);
}
