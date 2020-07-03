package com.example.reactorjava;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.streams.Book;

import reactor.core.publisher.Flux;

public class FluxDemo {

	public static void main(String[] args) {
	    
        Flux<String> flux= Flux.just("Spring MVC","Spring Boot","Spring Web");
        flux.subscribe(System.out::println);
        
System.out.println("------------------------------------------------------------------------------------------------------");
        
        Flux<Book> fluxArr= Flux.just(new Book(1,"HP Laptop",25000f),
                new Book(2,"Dell Laptop",30000f),
                new Book(3,"Lenevo Laptop",28000f),
                new Book(4,"Sony Laptop",28000f));
        fluxArr.subscribe(System.out::println);        
        
System.out.println("------------------------------------------------------------------------------------------------------");
        Flux<String> fluxfromArray = Flux.fromArray(new String[]{"Spring MVC","Spring Boot","Spring Web"});
        fluxfromArray.subscribe(System.out::println);

        System.out.println("----------------------------------------fromArray--------------------------------------------------------------");

        Flux<Book> fluxFromArr = Flux.fromArray(new Book[]{
                new Book(1,"HP Laptop",25000f),
                new Book(2,"Dell Laptop",30000f),
                new Book(3,"Lenevo Laptop",28000f),
                new Book(4,"Sony Laptop",28000f)});
        fluxFromArr.subscribe(System.out::println);

System.out.println("----------------------------------------------fluxFromIterable--------------------------------------------------------");
        
		List<Book> list = new ArrayList<Book>();		
		list.add(new Book(1,"HP Laptop",25000f));
		list.add(new Book(2,"Dell Laptop",30000f));
		list.add(new Book(3,"Lenevo Laptop",28000f));
		list.add(new Book(4,"Sony Laptop",28000f));
		list.add(new Book(5,"Apple Laptop",90000f));
		
        Flux<Book> fluxFromIterable=Flux.fromIterable(list);
        fluxFromIterable.subscribe(System.out::println);
        
System.out.println("------------------------------------------------------------------------------------------------------");
		
	}
	
	@Test
	public void testFluxConcatMerge() {
        Flux<Integer> evenNumb = Flux.range(0, 15)
                .filter(x -> x % 2 == 0); // i.e. 2, 4

        Flux<Integer> oddNumb = Flux.range(0, 15)
                .filter(x -> x % 2 > 0); // ie. 1, 3, 5
        
        Flux.concat(evenNumb, oddNumb).subscribe(s -> System.out.println(s));
        
        evenNumb.concatWith(oddNumb).subscribe(s -> System.out.println(s));
        
        Flux.merge(evenNumb, oddNumb).subscribe(s -> System.out.println(s));
        
        evenNumb.mergeWith(oddNumb).subscribe(s -> System.out.println(s));    
	}
	
    @Test
    public void testFluxZipWith01() {
        Flux<Integer> evenNumb = Flux.range(0, 15)
                .filter(x -> x % 2 == 0); // i.e. 2, 4

        Flux<Integer> oddNumb = Flux.range(0, 15)
                .filter(x -> x % 2 > 0); // ie. 1, 3, 5
        
        Flux.zip(evenNumb, oddNumb, (a, b) -> a + b)
            .subscribe(s -> System.out.println(s));

        evenNumb.zipWith(oddNumb, (a, b) -> a * b)
                .subscribe(s -> System.out.println(s));
    }
    
	@Test
	public void testFluxZipWith() {
        Flux<String> fluxA = Flux.just("A", "B", "C");
        Flux<String> fluxB = Flux.fromArray(new String[]{"D", "E", "F"});

        Flux.zip(fluxA , fluxB , (a, b) -> a + b)
            .subscribe(System.out::println);        
        
        fluxA.zipWith(fluxB, (a, b) -> a+"--"+b)
             .subscribe(System.out::println);
	}

}
