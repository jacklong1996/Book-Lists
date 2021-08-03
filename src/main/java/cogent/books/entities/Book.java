package cogent.books.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
	@Column(name = "SERIES")
	String series;
	
	@JsonBackReference
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "author_id")
	@Column(name = "AUTHOR")
	List<Author> authors;
	
	@JsonBackReference
	@ManyToMany(cascade = CascadeType.ALL)
	@Column(name = "GENRE")
	List<Genre> genre;
	
	
	@Column(name = "DESCRIPTION", length=10000)
	String description;
	@Column(name = "PAGES")
	int pages;
	@Column(name = "DATE")
	String date;
	@Column(name = "COVER")
	String cover;
	
	public void addAuthor(Author author) {
		if (authors == null) {
			authors = new ArrayList<Author>();
		}
		authors.add(author);
	}
	
	public void addGenre(Genre g) {
		if (genre == null) {
			genre = new ArrayList<Genre>();
		}
		genre.add(g);
	}
	
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
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
