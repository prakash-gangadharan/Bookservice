package com.webflux.funcendpoint.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.webflux.funcendpoint.model.Product;

public interface ProductRepository
        extends ReactiveMongoRepository<Product, String> {
}
