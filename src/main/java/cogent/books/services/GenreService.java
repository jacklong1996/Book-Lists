package cogent.books.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cogent.books.dao.GenreJPA;
import cogent.books.entities.Genre;



import java.util.Optional;

@Service
public class GenreService {
	@Autowired
	GenreJPA gRepo;
	
	public Genre findByName(String name) {
		Optional<Genre> genre = gRepo.findByName(name);
		return genre.get();
	}

}
