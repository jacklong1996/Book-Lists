package cogent.books.bookapis;



import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volume.VolumeInfo.ImageLinks;
import com.google.api.services.books.model.Volumes;
import com.google.api.services.books.model.Volumeseriesinfo;

import cogent.books.entities.Author;
import cogent.books.entities.Book;
import cogent.books.entities.Genre;
import cogent.books.services.BookService;

//import com.google.api.client.json.jackson2.JacksonFactory;

//import com.google.api.services.books.Books;
//import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.Books.Volumes.List;
//import com.google.api.services.books.model.Volume;
//import com.google.api.services.books.model.Volumes;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.NumberFormat;
//import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import java.util.List;

/**
 * A sample application that demonstrates how Google Books Client Library for
 * Java can be used to query Google Books. It accepts queries in the command
 * line, and prints the results to the console.
 *
 * $ java com.google.sample.books.BooksSample [--author|--isbn|--title] "<query>"
 *
 * Please start by reviewing the Google Books API documentation at:
 * http://code.google.com/apis/books/docs/getting_started.html
 */
@Service
public class BooksSample {

  /**
   * Be sure to specify the name of your application. If the application name is {@code null} or
   * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
   */
  private static final String APPLICATION_NAME = "";
  
  private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance();
  private static final NumberFormat PERCENT_FORMATTER = NumberFormat.getPercentInstance();
  
  @Autowired
  private BookService bs;
  
  
  private String addBooks(JsonFactory jsonFactory, String query) throws Exception {
	  ClientCredentials.errorIfNotSpecified();
	    
	    // Set up Books client.
	    final Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null)
	        .setApplicationName(APPLICATION_NAME)
	        .setGoogleClientRequestInitializer(new BooksRequestInitializer(ClientCredentials.API_KEY))
	        .build();
	    
	    // Set query string and filter only Google eBooks.
	    System.out.println("Query: [" + query + "]");
	    //List volumesList = books.volumes().list(query);
	    List volumesList = books.volumes().list(query);
	    volumesList.setFilter("ebooks");

	    // Execute the query.
	    Volumes volumes = volumesList.execute();
	    if (volumes.getTotalItems() == 0 || volumes.getItems() == null) {
	      System.out.println("No matches found.");
	      return "No matches found.";
	    }
	    
	    for (Volume volume : volumes.getItems()) {
	    	Book book = new Book();
	    	
	    	Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
	        ImageLinks il = volumeInfo.getImageLinks();
	        Volumeseriesinfo seriesInfo = volumeInfo.getSeriesInfo();
	        
	        /*if (seriesInfo.getShortSeriesBookTitle() != null)
	        	book.setSeries(seriesInfo.getShortSeriesBookTitle());*/
	        
	        book.setTitle(volumeInfo.getTitle());
	        
	        java.util.List<String> tempAuthors = volumeInfo.getAuthors();
	        java.util.List<Author> authors = new ArrayList<Author>();
	        for (String name : tempAuthors) {
	        	Author temp = new Author();
	        	temp.setName(name);
	        	//System.out.println(book.getTitle());
	        	temp.addBook(book);
	        	authors.add(temp);
	        }
	        book.setAuthors(authors);
	        
	        java.util.List<String> tempGenres = volumeInfo.getCategories();
	        java.util.List<Genre> genres = new ArrayList<Genre>();
	        for (String name : tempGenres) {
	        	Genre temp = new Genre();
	        	temp.setName(name);
	        	temp.addBook(book);
	        	genres.add(temp);
	        }
	        book.setGenre(genres);
	        
	        book.setPages(volumeInfo.getPageCount());
	        book.setDescription(volumeInfo.getDescription());
	        book.setDate(volumeInfo.getPublishedDate());
	        book.setCover(il.getMedium());
	        
	        System.out.print("Added: " + book.getTitle() +" by: ");
	        book.getAuthors().forEach(n -> n.print()); 
	        System.out.println(" to the db.");
	        
	        bs.save(book);
	    }
	    return "Added books to database.";
  }
  

  /*public static void main(String[] args) {
    //JsonFactory jsonFactory = JsonFactory.getDefaultInstance();
	  //JsonFactory jsonFactory = new JsonFactory();
	  JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
	  
    try {
      // Verify command line parameters.
      if (args.length == 0) {
        System.err.println("Usage: BooksSample [--author|--isbn|--title] \"<query>\"");
        System.exit(1);
      }
      // Parse command line parameters into a query.
      // Query format: "[<author|isbn|intitle>:]<query>"
      //String prefix = null;
      String prefix = "inauthor:";
      String query = "Orson Scott Card";

      try {
        //queryGoogleBooks(jsonFactory, query);
    	  query = prefix+query;
    	  addBooks(jsonFactory, query);
        // Success!
        return;
      } catch (IOException e) {
        System.err.println(e.getMessage());
      }
    } catch (Throwable t) {
      t.printStackTrace();
    }
    System.exit(0);
  }*/
  
  public String findBooks(String prefix, String search) {
	  String output = "";
	  JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
	  try {
		  if (prefix.equals("author")) {
			  return addBooks(jsonFactory, "inauthor:"+search);
		  } else if (prefix.equals("title")) {
			  return addBooks(jsonFactory, "intitle:"+search);
		  } else {
			  return "Not a search type.";
		  } 
	  } catch (IOException e) {
		  e.printStackTrace();
		  output = e.getMessage();
	  } catch (Throwable t) {
		  t.printStackTrace();
		  output = t.getMessage();
	  }
	  
	  return output;
  }
}