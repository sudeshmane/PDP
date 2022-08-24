package com.example.library.model;

public class Book 
{

	
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
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MMM-dd")
	public String getPublishDate() {
		return publishDate;
	}
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	private int id;
	private String name;
	
	String author;
	
	String publication;
	
	String publishDate;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
}