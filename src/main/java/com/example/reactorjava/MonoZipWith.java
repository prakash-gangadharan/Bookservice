package com.example.reactorjava;

import java.util.HashSet;
import com.example.streams.Book;

import reactor.core.publisher.Mono;

public class MonoZipWith {
	
	public static void main(String[] args) {
		
		Mono<Book> existingProductMono =  Mono.just(new Book(6,"HCL Laptop",35000f));
		Mono<Book> productMono =  Mono.just(new Book(7,"Samsung",35000f));
		
		Mono<HashSet> edited = productMono.zipWith(existingProductMono,(product, existingProduct) ->
                       // new Product(existingProduct.getId(), product.getName(), product.getPrice()))
		new HashSet())
              .log()
              .flatMap(product1 -> {return 
            		  Mono.just(product1);
              });
		
		System.out.println(edited.block().toString());
	}
}