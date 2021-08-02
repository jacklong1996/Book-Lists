package cogent.books.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "BOOK")
public class Book implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BOOK_ID")
	int id;
	@Column(name = "TITLE")
	String title;
	
	@JsonManagedReference
	@ManyToMany
	@Column(name = "AUTHOR")
	List<Author> authors;
	
	@JsonManagedReference
	@ManyToMany
	@Column(name = "GENRE")
	List<Genre> genre;
	
	
	@Column(name = "DESCRIPTION")
	String description;
	@Column(name = "PAGES")
	int pages;
	@Column(name = "YEAR")
	int year;
	@Column(name = "ISBN")
	int isbn;
	@Column(name = "COVER")
	String cover;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public List<Genre> getGenre() {
		return genre;
	}
	public void setGenre(List<Genre> genre) {
		this.genre = genre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
