package com.example.reactorjava;

import java.util.ArrayList;
import java.util.List;

import com.example.streams.Book;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Slf4j
public class MonoDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Mono<Book> mono1 =  Mono.just(new Book(6,"HCL Laptop",35000f));
		
		Mono<Book> mono2 =  Mono.just(new Book(7,"Samsung",35000f));
				
		Mono<Book> edited = mono2.flatMap( s -> {
					s.setName("Samsungedit");
					return Mono.just(new Book(s.getName().length(),s.getName(),35100f));
				 });
		
	      Mono<Object> edited = mono2.flatMap( s -> {
              s.setName("Samsungedit");
              return new Book(s.getName().length(),s.getName(),35100f);
           });
		
		//edited.subscribe(s -> System.out.println(s));
		edited.subscribe(id -> log.info("Result: {}", id));
				
		mono1.subscribe(s -> System.out.println("I need to see " + s.getName()));
				
		List<Book> list = new ArrayList<Book>();
		
		list.add(new Book(1,"HP Laptop",25000f));
		list.add(new Book(2,"Dell Laptop",30000f));
		list.add(new Book(3,"Lenevo Laptop",28000f));
		list.add(new Book(4,"Sony Laptop",28000f));
		list.add(new Book(5,"Apple Laptop",90000f));
		
		Mono<List<Book>> prodMono = Mono.just(list);
					
		prodMono.subscribe(s -> System.out.println(s));	

	    
	}

}
