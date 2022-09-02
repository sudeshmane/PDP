package com.example.library.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.library.entity.LibraryEntity;
import com.example.library.model.Book;
import com.example.library.repository.LibraryRepository;




@Service
public class LibraryService {
	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	LibraryRepository libraryRepository ;
	
	public List<LibraryEntity> getList(){
		return libraryRepository.findAll();
	}

	public LibraryEntity getLibrary(int id){
		return libraryRepository.findById(id).orElse(new LibraryEntity());
	}
	
	public List<Book> getBookListByUser(int userId){
		List<Book> bookList = new ArrayList<Book>();
		libraryRepository.findByUserid(userId).forEach(lib-> bookList.add(callBookService(lib.getBookId())));
		return bookList;
	}

	private Book callBookService(int bookId) {
		Book book =  (Book)restTemplate.getForObject("http://book-service/books/"+bookId, Book.class);
		return book;
	}
	public LibraryEntity addLibrary(int userId, int bookId) {
		LibraryEntity lib = new LibraryEntity();
		lib.setUserId(userId);
		lib.setBookId(bookId);
		return addLibrary(lib);
	}
	public LibraryEntity addLibrary(LibraryEntity lib){
		libraryRepository.save(lib);
		return lib;
	}

	public LibraryEntity update(LibraryEntity lib) {
		libraryRepository.save(lib);
		return null;
	}
	
	@Transactional
	public Long deletebyUserAndBook(int userId, int bookId) {
		return libraryRepository.deleteByUseridAndBookid(userId, bookId);
	}

	@Transactional
	public void deleteByUserId(int userId) {
		libraryRepository.deleteByUserid(userId);
	}
	
	@Transactional
	public void deleteByBookId(int bookId) {
		libraryRepository.deleteByBookid(bookId);
	}
	
	@Transactional
	public void deleteById(int libId) {
		libraryRepository.deleteById(libId);
	}

}
