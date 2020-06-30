package com.webflux.funcendpoint.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.webflux.funcendpoint.model.Book;


@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
