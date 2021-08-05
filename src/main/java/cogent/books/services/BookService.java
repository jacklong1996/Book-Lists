package cogent.books.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cogent.books.dao.AuthorJPA;
import cogent.books.dao.BookJPA;
import cogent.books.dao.GenreJPA;
import cogent.books.entities.Author;
import cogent.books.entities.Book;
import cogent.books.entities.Genre;

@Service
@Transactional
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
		
		List<Author> authors = book.getAuthors();
		//for (Author author: book.getAuthors()) {
		for (int i = 0; i < authors.size(); i++) {
			Author author = authors.get(i);
			Author a1 = aRepo.findByName(author.getName());
			if ( a1 == null) {
				System.out.println(author.getName());
				List<Book> bookList = new ArrayList<Book>();
				bookList.add(book);
				author.setBooks(bookList);
				//aRepo.save(author);
				authors.set(i, author);
			} else {
				a1.addBook(book);
				//aRepo.save(a1);
				authors.set(i, a1);
			}
		}
		book.setAuthors(authors);
		
		List<Genre> genres = book.getGenre();
		//for (Genre genre: book.getGenre()) {
		if (genres != null && genres.size() > 0) {
			for (int i = 0; i < genres.size(); i++) {
				Genre genre = genres.get(i);
				Genre g1 = gRepo.findByName(genre.getName());
				if (g1 == null) {
					List<Book> bookList = new ArrayList<Book>();
					bookList.add(book);
					genre.setBooks(bookList);
					//gRepo.save(genre);
					genres.set(i, genre);
				} else {
					g1.addBook(book);
					//gRepo.save(g1);
					genres.set(i, g1);
				}
			}
		}
		book.setGenre(genres);
		
		bRepo.save(book);
		//bRepo.saveAndFlush(book);
		
		return "Book inserted.";
	}
	
	public void delete(Book book) {
		
		List<Author> authors = new ArrayList<Author>();
		List<Genre> genres = new ArrayList<Genre>();
		book.setAuthors(authors);
		book.setGenre(genres);
		bRepo.save(book);
		bRepo.delete(book);
	}
	
}
