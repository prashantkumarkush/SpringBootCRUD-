package com.example.demo.Books;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Books {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookId;
	private String bookName;
	private String bookAuthorName;
	private Long price;
	private Boolean softDelete;
	
	public Books(Long bookId, String bookName, String bookAuthorName, Long price, Boolean softDelete) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookAuthorName = bookAuthorName;
		this.price = price;
		this.softDelete = softDelete;
	}
		
		public Boolean getSoftDelete() {
		return softDelete;
	}
	public void setSoftDelete(Boolean softdelete) {
		this.softDelete = softdelete;
	}
		
		public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthorName() {
		return bookAuthorName;
	}
	public void setBookAuthorName(String bookAuthorName) {
		this.bookAuthorName = bookAuthorName;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
		public Books() {
	
		// TODO Auto-generated constructor stub
	}

		
		
	
}