package com.example.library.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Library")
public class LibraryEntity 
{

	@Column 
	@GeneratedValue
	@Id
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	 
	
	
	
	public int getUserId() {
		return userid;
	}
	public void setUserId(int userId) {
		this.userid = userId;
	}
	public int getBookId() {
		return bookid;
	}
	public void setBookId(int bookId) {
		this.bookid = bookId;
	}



	@Column
	int userid;
	@Column
	int bookid;
	
	public LibraryEntity() {
		
	}
}