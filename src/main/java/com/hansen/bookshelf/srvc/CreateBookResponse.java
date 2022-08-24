package com.hansen.bookshelf.srvc;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hansen.bookshelf.model.Book;
import com.hansen.bookshelf.model.BookResponse;
import com.hansen.bookshelf.model.BookResponseList;

@Service
public class CreateBookResponse {
	private BookResponse bookResponse;
	private BookResponseList bookResponseList;
	
	public ResponseEntity<BookResponse> createResponse(Book book, String msg, HttpStatus status) {
		bookResponse = new BookResponse();
		bookResponse.setBook(book);
		bookResponse.setResponseMessage(msg);
		
		ResponseEntity<BookResponse> res;
		
		res = new ResponseEntity<BookResponse>(bookResponse, null, status);
		
		return res;
	}
	
	public ResponseEntity<BookResponseList> createResponseList(List<Book> books, String msg, HttpStatus status) {
		bookResponseList = new BookResponseList();
		bookResponseList.setBooks(books);
		bookResponseList.setResponseMessage(msg);
		
		ResponseEntity<BookResponseList> res;
		
		res = new ResponseEntity<BookResponseList>(bookResponseList, null, status);
		
		return res;
	}
	
	
}
