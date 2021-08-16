package cogent.books.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import cogent.books.entities.Author;

@CrossOrigin("http://localhost:4200")
public interface AuthorJPA extends JpaRepository<Author, Integer>{
	public Author findByName(String name);
}
