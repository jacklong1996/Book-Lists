package cogent.books.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cogent.books.dao.AuthorJPA;
import cogent.books.dao.BookJPA;
import cogent.books.dao.GenreJPA;
import cogent.books.entities.Author;
import cogent.books.entities.Book;
import cogent.books.entities.Genre;

public class BookService {
	@Autowired
	BookJPA bRepo;
	@Autowired
	AuthorJPA aRepo;
	@Autowired
	GenreJPA gRepo;
	
	public String save(Book book) {
		
		if (bRepo.findByTitle(book.getTitle()) != null) {
			return "Book already exists!";
		}
		
		for (Author author: book.getAuthors()) {
			Author a1 = aRepo.findByName(author.getName());
			if ( a1 == null) {
				List<Book> bookList = new ArrayList<Book>();
				bookList.add(book);
				author.setBooks(bookList);
				aRepo.save(author);
			} else {
				a1.addBook(book);
				aRepo.save(a1);
			}
		}
		
		for (Genre genre: book.getGenre()) {
			Genre g1 = gRepo.findByName(genre.getName());
			if (g1 == null) {
				List<Book> bookList = new ArrayList<Book>();
				bookList.add(book);
				g1.setBooks(bookList);
				gRepo.save(g1);
			} else {
				g1.addBook(book);
				gRepo.save(g1);
			}
		}
		
		bRepo.save(book);
		
		return "Book inserted.";
	}
}
