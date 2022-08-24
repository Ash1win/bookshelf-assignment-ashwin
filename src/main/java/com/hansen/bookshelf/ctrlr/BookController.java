package com.hansen.bookshelf.ctrlr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hansen.bookshelf.model.Book;
import com.hansen.bookshelf.model.BookResponse;
import com.hansen.bookshelf.model.BookResponseList;
import com.hansen.bookshelf.srvc.BookSrvc;
import com.hansen.bookshelf.srvc.CreateBookResponse;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	BookSrvc bookSrvc;
	@Autowired
	CreateBookResponse createBookResponse;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<BookResponse> create(@RequestBody Book bookToBeCreated) {
		ResponseEntity<BookResponse> bookResponse;
		Book book = bookSrvc.createBook(bookToBeCreated);
		
		if(book != null) {
			bookResponse = createBookResponse.createResponse(book, "book added!", HttpStatus.CREATED);			
		}else {
			bookResponse = createBookResponse.createResponse(null, "book already exists with given Id", HttpStatus.CONFLICT);
		}
		return bookResponse;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<BookResponseList> readAll() {
		ResponseEntity<BookResponseList> bookResponse;
		
		
		List<Book> bookList = bookSrvc.getAllBooks();
		
		if(bookList.size() > 0) {
			bookResponse = createBookResponse.createResponseList(bookList, "books are present", HttpStatus.OK);
		}else {
			bookResponse = createBookResponse.createResponseList(bookList, "books are not present", HttpStatus.OK);
		}
		
		return bookResponse;
	}
	
	@RequestMapping(value = "{bookId}", method = RequestMethod.GET)
	public ResponseEntity<BookResponse> read(@PathVariable(value = "bookId") Integer bookId) {
		ResponseEntity<BookResponse> bookResponse;
		
		
		Book book = bookSrvc.getBook(bookId);
		
		if(book != null) {
			bookResponse = createBookResponse.createResponse(book, "book exist", HttpStatus.OK);			
		}else {
			bookResponse = createBookResponse.createResponse(book, "book dose not exist", HttpStatus.NOT_FOUND);			
		}
		return bookResponse;
	}
	
	@RequestMapping(method = RequestMethod.PATCH) //OR PUT
	public ResponseEntity<BookResponse> update(@RequestBody Book bookToBeUpdated) {
		ResponseEntity<BookResponse> bookResponse;
		
		
		Book book = bookSrvc.updateBook(bookToBeUpdated);
		
		if(book != null) {
			bookResponse = createBookResponse.createResponse(book, "book updated", HttpStatus.OK);			
		}else {
			bookResponse = createBookResponse.createResponse(book, "could not update book with invalid Id", HttpStatus.NOT_FOUND);						
		}
		return bookResponse;
	}
	
	@RequestMapping(value = "{bookId}", method = RequestMethod.DELETE)
	public ResponseEntity<BookResponse> delete(@PathVariable(value = "bookId") Integer bookId) {
		ResponseEntity<BookResponse> bookResponse;
		
		
		boolean isdeleted = bookSrvc.deleteBook(bookId);
		
		
		
		if (isdeleted) {
			bookResponse = createBookResponse.createResponse(null, "book deleted", HttpStatus.OK);
		} else {
			bookResponse = createBookResponse.createResponse(null, "book dose not exist with given Id", HttpStatus.NOT_FOUND);
		}
		return bookResponse;
	}

	
}
