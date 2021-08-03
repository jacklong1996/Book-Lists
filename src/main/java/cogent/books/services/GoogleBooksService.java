package cogent.books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.books.bookapis.BooksSample;

@Service
public class GoogleBooksService {
	@Autowired
	BooksSample bs;
	
	public String addBook(String prefix, String search) {
		return bs.findBooks(prefix, search);
	}
}
