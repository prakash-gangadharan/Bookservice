package com.example.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> strings = Arrays.asList("Volvo", "BMW", "Ford", "Mazda");
		
		System.out.println("List<String> strings : " + strings);
		
		// List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
		
		Stream<String> stream = strings.stream().map(car -> car+ " - car ");
		
		stream.forEach(System.out::println);
		
		Stream<String> stream1 = strings.stream();
		
		System.out.println(stream1.collect(Collectors.toList()));
		
	}

}
