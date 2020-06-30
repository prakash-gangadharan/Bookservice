package com.webflux.funcendpoint.handler;

import java.security.Principal;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.webflux.funcendpoint.model.Book;
import com.webflux.funcendpoint.service.BookService;

import reactor.core.publisher.Mono;

@Component
public class BookHandler {
	private final BookService bookService;
	
	public BookHandler(BookService bookService) {
		this.bookService = bookService;
	}
	
	public Mono<ServerResponse> findById(ServerRequest serverRequest) {
		String id  =serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookService.findById(id), Book.class);
	}
	
	public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(bookService.findAll(), Book.class);
	}
	
	
    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        final Mono<Book> book = serverRequest.bodyToMono(Book.class);
        
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(book.flatMap(bookService::save), Book.class));
    }
    
    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookService.deleteById(id), Void.class);
    }
    
    @SuppressWarnings("deprecation")
	public Mono<ServerResponse> reactive(ServerRequest serverRequest) {
        return Mono.zip(
                serverRequest.principal(),
                serverRequest.bodyToMono(String.class)
        ).flatMap(tuple -> {
            Principal principal = tuple.getT1();
            System.out.println(principal);
            String body = tuple.getT2();
            System.out.println(body);
            return ServerResponse.ok().build();
        });
    }
}
