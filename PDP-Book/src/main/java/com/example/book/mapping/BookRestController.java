package com.example.book.mapping;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.book.model.Book;
import com.example.book.service.BookService;

@RestController
public class BookRestController {

	@Autowired
	BookService bookService;
	
	
	
	@GetMapping("/books")
	public ResponseEntity<Map<Integer, Book>> getBookList(){
		//User user = restTemplate.getForObject("http://localhost:61042/users/10", User.class);
		//System.out.println(user.getName());
		return new ResponseEntity(bookService.getList(), HttpStatus.OK );
	}
	
	@GetMapping("/books/{book_id}")
	public Book getBook(@PathVariable ("book_id") int bookId){
		Book book = bookService.getBook(bookId);
		book.setId(bookId);
		return book;
	}
	
	@PostMapping(value="/books")
	public Book addBook(@RequestBody Book book){
		return bookService.addBook(book);
	}
	
	@PutMapping(value="/books/{book_id}")
	public Book updateBook(@PathVariable ("book_id") int bookId, @RequestBody Book book){
		book.setId(bookId);
		return bookService.update(book);
	}
	
	@DeleteMapping(value="/books/{book_id}")
	public void deleteBook(@PathVariable ("book_id") int bookId){
		 bookService.deleteById(bookId);
		 
	}
}
