package cogent.books.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cogent.books.dao.GenreJPA;
import cogent.books.entities.Genre;


@Service
@Transactional
public class GenreService {
		@Autowired
		GenreJPA gRepo;
		
		public Genre findGenreByName(String string){
			return gRepo.findByName(string);
			
		}
}
