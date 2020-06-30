package com.example.webflux.bookservice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.webflux.bookservice.model.Book;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
