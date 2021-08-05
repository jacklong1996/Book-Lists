package cogent.books.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.books.dao.GenreJPA;
import cogent.books.entities.Genre;

@Service
public class GenreService {
	@Autowired
	GenreJPA gRepo;
	
	public Genre findByName(String name) {
		Optional<Genre> genre = gRepo.findByName(name);
		
		return genre.get();
	}
}
