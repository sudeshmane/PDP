package com.example.user.model;

import java.io.Serializable;

public class User implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 368448686277510545L;
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
	/*
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
	public LocalDate getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}
	*/
	String name;
	//String author;
	//String publication;
	//LocalDate publishDate;
}
