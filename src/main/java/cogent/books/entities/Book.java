package cogent.books.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "BOOK")
public class Book implements Serializable {
	@Id
	@Column(name = "BOOK_ID")
	int id;
	@Column(name = "TITLE")
	String title;
	@Column
}
