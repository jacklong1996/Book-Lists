package cogent.books.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cogent.books.entities.User;

public interface UserJPA extends JpaRepository<User, Integer>{

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
}
