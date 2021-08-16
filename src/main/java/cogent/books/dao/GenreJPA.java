package cogent.books.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import cogent.books.entities.Genre;

@CrossOrigin("http://localhost:4200")
public interface GenreJPA extends JpaRepository<Genre, Integer>{
	public Optional<Genre> findByName(String name);
	
}
