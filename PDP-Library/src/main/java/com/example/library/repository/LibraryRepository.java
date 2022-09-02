package com.example.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.entity.LibraryEntity;

@Repository
public interface LibraryRepository extends JpaRepository<LibraryEntity, Integer>
{

	Long deleteByUserid(Integer userId);
	
	Long deleteByUseridAndBookid(Integer userId, Integer bookId);

	List<LibraryEntity> findByUserid(Integer userId);

	Integer deleteByBookid(Integer bookId);
	
}