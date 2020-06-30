package com.example.reactorjava;

import java.util.ArrayList;
import java.util.List;

import com.example.streams.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public class MonoDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Mono<Book> mono1 =  Mono.just(new Book(6,"HCL Laptop",35000f));
		
		Mono<Book> mono2 =  Mono.just(new Book(7,"Samsung",35000f));
				
		Mono<Book> edited = mono2.flatMap( s -> {
					s.setName("Samsungedit");
					return Mono.just(new Book(s.getName().length(),s.getName(),35100f));
				 });
		
		edited.subscribe(s -> System.out.println(s));
				
		mono1.subscribe(s -> System.out.println("I need to see " + s.getName()));
				
		List<Book> list = new ArrayList<Book>();
		
		list.add(new Book(1,"HP Laptop",25000f));
		list.add(new Book(2,"Dell Laptop",30000f));
		list.add(new Book(3,"Lenevo Laptop",28000f));
		list.add(new Book(4,"Sony Laptop",28000f));
		list.add(new Book(5,"Apple Laptop",90000f));
		
		Mono<List<Book>> prodMono = Mono.just(list);
					
		prodMono.subscribe(s -> System.out.println(s));

		Flux<Integer> evenNumb = Flux
						  .range(0, 15)
						  .filter(x -> x % 2 == 0);
		
		Flux<Integer> oddNumb = Flux
						  .range(0, 15)
						  .filter(x -> x % 2 > 0);  // ie. 1, 3, 5
	
//		Flux.concat(evenNumb, oddNumb)
//			.subscribe(System.out::println);
		
//		evenNumb.concatWith(oddNumb)
//				.subscribe(System.out::println);
		
//		Flux.merge(evenNumb, oddNumb)
//			.subscribe(System.out::println);
		
//		evenNumb.mergeWith(oddNumb)
//				.subscribe(System.out::println);
		
		Flux<String> fluxA = Flux.just("A", "B", "C");
		Flux<String> fluxB = Flux.fromArray(new String[]{"D", "E", "F"});

	    Flux.zip(fluxA , fluxB , (a, b) -> a + b)
			.subscribe(System.out::println);		
		
		fluxA.zipWith(fluxB, (a, b) -> a+"--"+b)
			 .subscribe(System.out::println);
	    
	}

}