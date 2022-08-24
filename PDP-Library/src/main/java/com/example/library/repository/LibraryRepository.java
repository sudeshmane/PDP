package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.entity.LibraryEntity;

@Repository
public interface LibraryRepository extends JpaRepository<LibraryEntity, Integer>
{

	
	
}