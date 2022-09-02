package com.example.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.stereotype.Service;

import com.example.book.model.Book;
import com.example.book.repository.BookRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
@EnableHystrixDashboard
public class BookService {
	
	@Autowired
	BookRepository bookRepository;

	
	public List<Book> getList(){
		return bookRepository.findAll();
	}

	@HystrixCommand(fallbackMethod = "getHystrixBook")
	public Book getBook(int id){
		return bookRepository.findById(id).get();//.orElseGet(()-> new Book());
	}

	public Book getHystrixBook(int id){
		Book book = new Book();
		book.setId(0);
		book.setAuthor("Hystrix Author");
		book.setName("Hystrix");
		return book;
	}
	public Book addBook(Book book){
		bookRepository.save(book);
		return book;
	}

	public Book update(Book book) {
		bookRepository.save(book);
		return null;
	}

	public void deleteById(int bookId) {
		bookRepository.delete(getBook(bookId));
	}

}
