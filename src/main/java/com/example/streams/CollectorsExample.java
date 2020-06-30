package com.example.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CollectorsExample {

    public static void main(String[] args) {
        List<Book> list = new ArrayList<Book>();

        list.add(new Book(1, "HP Laptop", 25000f));
        list.add(new Book(2, "Dell Laptop", 30000f));
        list.add(new Book(3, "Lenevo Laptop", 28000f));
        list.add(new Book(4, "Sony Laptop", 28000f));
        list.add(new Book(5, "Apple Laptop", 90000f));

        System.out.println(list);

        Stream<Book> bookStream = list.stream();
        Stream<String> nameStrm = bookStream.map(prod -> prod.name);
        nameStrm.forEach(System.out::println);
    }

}
