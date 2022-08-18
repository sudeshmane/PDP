package com.example.book.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="Book")
public class Book 
{

	@Id
	@GeneratedValue
	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	 public String getAuthor() {
	 	return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublication() {
		return publication;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd")
	public LocalDate getPublishDate() {
		return publishDate;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}
	
	@Column
	private String name;
	@Column
	String author;
	@Column
	String publication;
	@Column
	LocalDate publishDate;
}