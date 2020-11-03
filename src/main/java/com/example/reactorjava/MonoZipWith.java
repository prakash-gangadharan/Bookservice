package com.example.reactorjava;

import com.example.streams.Book;

import reactor.core.publisher.Mono;

public class MonoZipWith {
	
	public static void main(String[] args) {
		
		Mono<Book> existingBookMono =  Mono.just(new Book(6,"HCL Laptop",35000f));
		Mono<Book> BookMono =  Mono.just(new Book(7,"Samsung",35000f));
		
		Mono<Object> edited = BookMono
		                    .zipWith(existingBookMono, (book, existingBook) ->
	                        new Book(existingBook.getId(), book.getName(), book.getPrice()))
		                    .log()
		                    .flatMap(product -> {return  Mono.just(product);
              });
		
		System.out.println(edited.block().toString());
		
		Mono.zip(existingBookMono, BookMono ,(a, b) -> a.equals(b) ).subscribe(System.out::println); 
		
		Mono.zip(existingBookMono, BookMono ,
		    (existingBook, book) -> new Book(existingBook.getId(), book.getName(), book.getPrice()))
		    .flatMap(s -> {return Mono.just(s);})
		    .subscribe(System.out::println); 
		
		Mono.zip(existingBookMono, BookMono)
		.flatMap(tuple ->{
		    Book exBook = tuple.getT1();
		    Book book = tuple.getT2();
		    return  Mono.just(new Book(exBook.getId(), book.getName(), book.getPrice()));
		}) .subscribe(System.out::println); 
        
	}
}