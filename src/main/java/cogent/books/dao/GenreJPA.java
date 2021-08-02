package cogent.books.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cogent.books.entities.Genre;

public interface GenreJPA extends JpaRepository<Genre, Integer>{
	public Genre findByName(String name);
}
