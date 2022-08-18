package com.example.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book.model.Book;
import com.example.book.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;

	
	public List<Book> getList(){
		return bookRepository.findAll();
	}

	public Book getBook(int id){
		return bookRepository.findById(id).get();
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
