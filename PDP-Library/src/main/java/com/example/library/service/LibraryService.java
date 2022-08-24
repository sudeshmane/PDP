package com.example.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.LibraryEntity;
import com.example.library.repository.LibraryRepository;

@Service
public class LibraryService {
	
	//Map<Integer, User> userMap = new HashMap<>();
	@Autowired
	LibraryRepository libraryRepository ;
	
	public List<LibraryEntity> getList(){
		return libraryRepository.findAll();
	}

	public LibraryEntity getLibrary(int id){
		return libraryRepository.findById(id).get();
	}

	public LibraryEntity addLibrary(LibraryEntity lib){
		libraryRepository.save(lib);
		return lib;
	}

	public LibraryEntity update(LibraryEntity lib) {
		libraryRepository.save(lib);
		return null;
	}

	public void deleteById(int libId) {
		libraryRepository.delete(getLibrary(libId));
	}

}
