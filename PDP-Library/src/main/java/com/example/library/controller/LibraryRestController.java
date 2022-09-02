package com.example.library.controller;



import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.library.entity.LibraryEntity;
import com.example.library.model.Book;
import com.example.library.model.User;
import com.example.library.service.LibraryService;





@RestController
public class LibraryRestController {

	
	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	DiscoveryClient discovery;
	
	@Autowired
	LibraryService libService;
	
	Logger logger = (Logger) LoggerFactory.getLogger(LibraryRestController.class);
	
	@GetMapping("/library/books")
	public 	List<Book> getBookList(){
		logger.info("in get Booklist ");
		List<Book> book =  (List<Book>)restTemplate.getForObject("http://book-service/books", List.class);
		return book;
	}
	
	
	@GetMapping("/library/books/{book_id}")
	public 	Book getBook(@PathVariable ("book_id") int bookId){
		logger.info("in get Book ");
		Book book =  (Book)restTemplate.getForObject("http://book-service/books/"+bookId, Book.class);
		return book;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN2')")
	@PostMapping("/library/books")
	public 	Book addBook(@RequestBody Book  book){
		logger.info("in add Book ADMIN2 ");
		Book retBook =  (Book)restTemplate.postForObject("http://book-service/books",book, Book.class);
		return retBook;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN2')")
	@DeleteMapping("/library/books/{book_id}")
	public 	void deleteBook(@PathVariable ("book_id") int bookId){
		logger.info("in delete Book ADMIN2 ");
		libService.deleteByBookId(bookId);
		restTemplate.delete("http://book-service/books/"+bookId);
	}
	
	
	@GetMapping("/library/users")
	public List<User> getUserList(){
		logger.info("in get Userlist ");
		URI uri = discovery.getInstances("user-service").get(0).getUri();
		//return (List<Book>) restTemplate.getForObject(uri+"/books/", List.class);
		List<User> users =  restTemplate.getForObject("http://user-service/users", List.class);
		System.out.println("Afterrrrrr");
		return users;
		//return userService.getList();
	}
	
	@GetMapping("/library/users/{user_id}")
	public 	List<Book> getUser(@PathVariable ("user_id") int userId){
		logger.info("in get User ");
		User user =  (User)restTemplate.getForObject("http://user-service/users/"+userId, User.class);
		return libService.getBookListByUser(user.getId());
	}
	
	@PreAuthorize("hasAnyRole('ADMIN2')")
	@PostMapping("/library/users")
	public 	User addUser(@RequestBody User user){
		logger.info("in add Book ADMIN2 ");
		User retUser =  (User)restTemplate.postForObject("http://user-service/users",user, User.class);
		return retUser;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN2')")
	@DeleteMapping("/library/users/{user_id}")
	public 	void deleteUser(@PathVariable ("user_id") int userId){
		logger.info("in delete User ADMIN2 ");
		libService.deleteById(userId);
		restTemplate.delete("http://user-service/books/"+userId);
	}
	
	
	@PostMapping("/library/users/{user_id}/books/{book_id}")
	public 	LibraryEntity createLib(@PathVariable("user_id") int userId, @PathVariable("book_id") int bookId){
		logger.info("in getcreate Lib ");
		return libService.addLibrary(userId, bookId);
	}
	
	@DeleteMapping("/library/users/{user_id}/books/{book_id}")
	public 	Long deleteLibbyUserAndBook(@PathVariable("user_id") int userId, @PathVariable("book_id") int bookId){
		return libService.deletebyUserAndBook(userId, bookId);
	}
	
	@PutMapping("/library/users/{user_id}")
	public void updateLibbyUser(@PathVariable("user_id") int userId, @RequestBody User user ){
		logger.info("in update LibbyUser ");
		restTemplate.put("http://user-service/users/"+userId, user);
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN2')")
	@GetMapping("/lib")
		public 	String getString(){
		logger.info("in test ADMIN2 ");
		return "Sudesh";
	}
	
		
	
	/*
	 @PostMapping(value="/library/books/{book_id}")
	 public Book addUser(@PathVariable ("book_id") int bookId){
		Book book =  restTemplate.getForObject("http://book-service/books/"+bookId, Book.class);
		//return libraryService.addLibrary(book);
	}
	*/
	
	
	
	
}
