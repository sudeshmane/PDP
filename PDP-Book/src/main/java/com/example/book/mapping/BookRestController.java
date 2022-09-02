package com.example.book.mapping;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.model.Book;
import com.example.book.service.BookService;

@RestController
public class BookRestController {

	@Autowired
	BookService bookService;
	
	Logger logger = (Logger) LoggerFactory.getLogger(BookRestController.class);
	
	
	@GetMapping("/books")
	public ResponseEntity<Map<Integer, Book>> getBookList(){
		logger.info("in Book getBookList");
		//User user = restTemplate.getForObject("http://localhost:61042/users/10", User.class);
		//System.out.println(user.getName());
		return new ResponseEntity(bookService.getList(), HttpStatus.OK );
	}
	
	
	//@HystrixCommand(fallbackMethod = "getBookHystrix")
	@GetMapping("/books/{book_id}")
	public Book getBook(@PathVariable ("book_id") int bookId){
		logger.info("in Book getBook");
		Book book = bookService.getBook(bookId);
		book.setId(bookId);
		return book;
	}
	
	public Book getBookHystrix(int id){
		logger.info("in Book getBook Hystrix");
		Book book = new Book();
		book.setId(0);
		book.setName("Hystrix Book");
		book.setAuthor("Hystrix Author");
		book.setPublication("Hystrix Publication");
		return book;
	}
	
	@PostMapping(value="/books")
	public Book addBook(@RequestBody Book book){
		logger.info("in Book addBook");
		return bookService.addBook(book);
	}
	
	@PutMapping(value="/books/{book_id}")
	public Book updateBook(@PathVariable ("book_id") int bookId, @RequestBody Book book){
		logger.info("in Book updateBook");
		book.setId(bookId);
		return bookService.update(book);
	}
	
	@DeleteMapping(value="/books/{book_id}")
	public void deleteBook(@PathVariable ("book_id") int bookId){
		logger.info("in Book deleteBookList");
		 bookService.deleteById(bookId);
		 
	}
}
