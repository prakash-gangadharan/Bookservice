package com.example.reactorjava;

import java.util.ArrayList;
import java.util.List;

import com.example.streams.Book;

import reactor.core.publisher.Mono;

public class MonoFlatMap {

	public static void main(String[] args) {
		List<Book> list = new ArrayList<Book>();
		
		list.add(new Book(1,"HP Laptop",25000f));
		list.add(new Book(2,"Dell Laptop",30000f));
		list.add(new Book(3,"Lenevo Laptop",28000f));
		list.add(new Book(4,"Sony Laptop",28000f));
		list.add(new Book(5,"Apple Laptop",90000f));
		
		Mono<List<Book>> prodMono = Mono.just(list);
		
		prodMono
			.map(book-> {
				return book;
			})
			.flatMap(s1-> {
				System.out.println("s : " +  s1);
				return Mono.just(s1);
			})
			.subscribe(s -> System.out.println("s : " +  s));
	}

}
