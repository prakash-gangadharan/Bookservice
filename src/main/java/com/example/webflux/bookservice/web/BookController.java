package com.example.webflux.bookservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webflux.bookservice.model.Book;
import com.example.webflux.bookservice.service.BookService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BookController {
	
	private BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping(value = "/book/{id}")
	public Mono<ResponseEntity<Book>> getBookById(@PathVariable String id) {
		return bookService.findById(id)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.status(404).body(null));
				
	}

	@GetMapping(value = "/books")
	public Flux<Book> getAllBooks() {
		return bookService.findAll();
	}

	@PostMapping(value = "/book")
	public Mono<Book> createBook(@RequestBody Book book) {
		return bookService.save(book);
	}
	
	@DeleteMapping(value = "/book/{id}")
	public Mono<Void> deleteBookById(@PathVariable String id) {
		return bookService.deleteById(id);
	}
	
}
