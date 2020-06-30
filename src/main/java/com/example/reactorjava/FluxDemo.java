package com.example.reactorjava;

import java.util.ArrayList;
import java.util.List;

import com.example.streams.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Mono<Book> mono =  Mono.just(new Book(1,"HP Laptop",25000f));
				
		mono.subscribe(s -> System.out.println("I need to see " + s.getName()));
				
		List<Book> list = new ArrayList<Book>();
		
		list.add(new Book(1,"HP Laptop",25000f));
		list.add(new Book(2,"Dell Laptop",30000f));
		list.add(new Book(3,"Lenevo Laptop",28000f));
		list.add(new Book(4,"Sony Laptop",28000f));
		list.add(new Book(5,"Apple Laptop",90000f));
		
		Flux<List<Book>> intFlux = Flux.just(list);
					
		//intFlux.subscribe(s -> System.out.println(s));
		

		Flux<Integer> evenNumb = Flux
						  .range(0, 15)
						  .filter(x -> x % 2 == 0); // i.e. 2, 4
		
		Flux<Integer> oddNumb = Flux
						  .range(0, 15)
						  .filter(x -> x % 2 > 0);  // ie. 1, 3, 5
		
		Flux<Integer> concat1 = Flux.concat(evenNumb, oddNumb);
		//concat1.subscribe(s -> System.out.println(s));
		
		Flux<Integer> concat2 = evenNumb.concatWith(oddNumb);
		//concat2.subscribe(s -> System.out.println(s));
		
		Flux<Integer> merge = Flux.merge(evenNumb, oddNumb);
		//concat3.subscribe(s -> System.out.println(s));
		
		Flux<Integer> mergeWith = evenNumb.mergeWith(oddNumb);
		//mergeWith.subscribe(s -> System.out.println(s));
			    
	    Flux<Integer> zip = Flux.zip(
	    						evenNumb, 
	    						oddNumb, 
	    						(a, b) -> a + b);
	    //zip.subscribe(s -> System.out.println(s));
	    
	    Flux<Integer> zipWith = evenNumb.zipWith(oddNumb, (a, b) -> a * b);
	    
	    zipWith.subscribe(s -> System.out.println(s));
	}

}
