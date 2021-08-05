package cogent.books.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cogent.books.entities.Genre;

public interface GenreJPA extends JpaRepository<Genre, Integer>{

	public Optional<Genre> findByName(String name);
}
