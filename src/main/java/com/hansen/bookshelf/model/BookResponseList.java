package com.hansen.bookshelf.model;

import java.util.List;

public class BookResponseList {
	private List<Book> books;
	private String responseMessage;
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
}
