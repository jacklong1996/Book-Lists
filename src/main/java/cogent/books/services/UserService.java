package cogent.books.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.books.dao.BookJPA;
import cogent.books.dao.UserJPA;
import cogent.books.entities.Book;
import cogent.books.entities.User;

@Service
public class UserService {
	@Autowired
	UserJPA uRepo;
	@Autowired
	BookJPA bRepo;
	
	public void addUser(User user) {
		List<Book> book1 = new ArrayList<Book>();
		List<Book> book2 = new ArrayList<Book>();
		
		user.setRead(book1);
		user.setWant(book2);
		
		uRepo.save(user);
	}
	
	public String addWant(int uid, int bid) {
		Optional<Book> book = bRepo.findById(bid);
		Optional<User> user = uRepo.findById(uid);
		
		if (book.isPresent() && user.isPresent()) {
			user.get().addBooktoWant(book.get());
			uRepo.save(user.get());
			return "Book added";
		} else {
			return "Couldn't find either user or book";
		}
	}
	
	public String addRead(int uid, int bid) {
		Optional<Book> book = bRepo.findById(bid);
		Optional<User> user = uRepo.findById(uid);
		
		if (book.isPresent() && user.isPresent()) {
			user.get().addBooktoRead(book.get());
			uRepo.save(user.get());
			return "Book added";
		} else {
			return "Couldn't find either user or book";
		}
	}
	
	public User findById(int uid) {
		return uRepo.getById(uid);
	}
	
	public String addToWant(int uid, int bid) {
		User u = uRepo.getById(uid);
		for (Book b : u.getWant()) {
			if (b.getId() == bid)
				return "Book was already in list";
		}
		for (Book b: u.getRead()) {
			if (b.getId() == bid) {
				u.removeBookRead(b);
			}
		}
		Book b = bRepo.getById(bid);
		u.addBooktoWant(b);
		uRepo.save(u);
		return "Added book to want to read list";
	}
	
	public String addToRead(int uid, int bid) {
		User u = uRepo.getById(uid);
		for (Book b : u.getRead()) {
			if (b.getId() == bid)
				return "Book was already in list";
		}
		for (Book b: u.getWant()) {
			if (b.getId() == bid) {
				u.removeBookWant(b);
			}
		}
		Book b = bRepo.getById(bid);
		u.addBooktoRead(b);
		uRepo.save(u);
		return "Added book to want to read list";
	}
}
