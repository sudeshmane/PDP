package com.example.library.controller;



import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.library.model.Book;
//import com.example.library.model.Book;
import com.example.library.model.User;




@RestController
public class LibraryRestController {

	
	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	DiscoveryClient discovery;
	
	@PreAuthorize("hasAnyRole('ADMIN2')")
	@GetMapping("/library")
	public List<User> getUserList(){
		URI uri = discovery.getInstances("user-service").get(0).getUri();
		System.out.println(uri);
		//return (List<Book>) restTemplate.getForObject(uri+"/books/", List.class);
		List<User> users =  restTemplate.getForObject("http://user-service/users", List.class);
		System.out.println("Afterrrrrr");
		return users;
		//return userService.getList();
	}
	
	@GetMapping("/library/books/{book_id}")
	public 	Book getBook(@PathVariable ("book_id") int bookId){
		Book book =  (Book)restTemplate.getForObject("http://book-service/books/"+bookId, Book.class);
		return book;
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN2')")
	@GetMapping("/lib")
		public 	String getString(){
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
