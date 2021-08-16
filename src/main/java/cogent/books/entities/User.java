package cogent.books.entities;

	import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

	@Entity
	@Table(name = "users", uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") 
		})

	public class User {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="USER_ID")
		private int id;

		@NotBlank
		@Size(max = 20)
		@Column(name="USERNAME")
		private String username;

		@NotBlank
		@Size(max = 50)
		@Email
		@Column(name="EMAIL")
		private String email;

		@NotBlank
		@Size(max = 120)
		@Column(name="PASSWORD")
		private String password;
		
		@Size(max = 20)
		@Column(name = "NAME")
		private String name;

		@OneToMany
		@Column(name="WANT")
		private List<Book> want;
		
		@OneToMany
		@Column(name="READ")
		private List<Book> read;
		
		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
		private Set<Role> roles;
		
		public void addBooktoWant(Book b) {
			want.add(b);
		}
		
		public void addBooktoRead(Book b) {
			read.add(b);
		}
		
		public void removeBookWant(Book b) {
			want.remove(b);
		}
		
		public void removeBookRead(Book b) {
			read.remove(b);
		}
		
		public User() {
		}

		public User(String username, String email, String password, String name) {
			this.username = username;
			this.email = email;
			this.password = password;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public List<Book> getWant() {
			return want;
		}

		public void setWant(List<Book> want) {
			this.want = want;
		}

		public List<Book> getRead() {
			return read;
		}

		public void setRead(List<Book> read) {
			this.read = read;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Set<Role> getRoles() {
			return roles;
		}

		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}
		
		
	}