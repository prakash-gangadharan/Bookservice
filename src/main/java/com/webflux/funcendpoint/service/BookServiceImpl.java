package com.webflux.funcendpoint.service;

import org.springframework.stereotype.Service;

import com.webflux.funcendpoint.model.Book;
import com.webflux.funcendpoint.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class BookServiceImpl implements BookService{
	private BookRepository bookRepository;
	
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
	@Override
	public Mono<Book> findById(String id) {
		return bookRepository.findById(id);
	}

	@Override
	public Flux<Book> findAll() {
	       log.error("log.error >> log.error");
	        log.debug("log.debug >> log.debug");
	        log.info("log.info >> log.info");
	        log.warn("log.warn >> log.warn");
	        
		return bookRepository.findAll();
	}

	@Override
	public Mono<Book> save(Book book) {
		 return bookRepository.save(book);
	}

	@Override
	public Mono<Void> deleteById(String id) {
		return bookRepository.deleteById(id);
	}

}
