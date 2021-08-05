package cogent.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cogent.books.entities.Genre;
import cogent.books.services.GenreService;

@RestController
@RequestMapping("/rest")
public class GenreController {

	@Autowired
	GenreService gserv;

	@GetMapping("/genre")
	public Genre genre(@RequestParam("name") String name) {
		return gserv.findByName(name);

	}
}
