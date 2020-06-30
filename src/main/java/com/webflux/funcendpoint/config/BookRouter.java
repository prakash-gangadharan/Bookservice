package com.webflux.funcendpoint.config;

import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.webflux.funcendpoint.handler.BookHandler;
import com.webflux.funcendpoint.handler.ProductHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RequestPredicates.method;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;;

@ComponentScan(basePackages = "com.webflux.funcendpoint")
@Configuration
public class BookRouter {
    
    private final BookHandler handler;

    public BookRouter(BookHandler handler) {
        this.handler = handler;
    }
	
	@Bean
	public RouterFunction<ServerResponse> route01() {
		return RouterFunctions
				.route(GET("/fbook").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
				.andRoute(GET("/fbook/{id}").and(accept(MediaType.APPLICATION_STREAM_JSON)), handler::findById)
				.andRoute(POST("/fbook").and(accept(MediaType.APPLICATION_JSON)), handler::save)
				.andRoute(DELETE("/fbook/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::delete)
				.andRoute(GET("/reactive").and(accept(MediaType.APPLICATION_JSON)), handler::reactive);
	}

    @Bean
    RouterFunction<ServerResponse> routes(ProductHandler handler) {
//      return route(GET("/products").and(accept(APPLICATION_JSON)), handler::getAllProducts)
//              .andRoute(POST("/products").and(contentType(APPLICATION_JSON)), handler::saveProduct)
//              .andRoute(DELETE("/products").and(accept(APPLICATION_JSON)), handler::deleteAllProducts)
//              .andRoute(GET("/products/events").and(accept(TEXT_EVENT_STREAM)), handler::getProductEvents)
//              .andRoute(GET("/products/{id}").and(accept(APPLICATION_JSON)), handler::getProduct)
//              .andRoute(PUT("/products/{id}").and(contentType(APPLICATION_JSON)), handler::updateProduct)
//              .andRoute(DELETE("/products/{id}").and(accept(APPLICATION_JSON)), handler::deleteProduct);

        return nest(path("/products"),
                nest(accept(APPLICATION_JSON).or(contentType(APPLICATION_JSON)).or(accept(TEXT_EVENT_STREAM)),
                        route(GET("/"), handler::getAllProducts)
                                .andRoute(method(HttpMethod.POST), handler::saveProduct)
                                .andRoute(DELETE("/"), handler::deleteAllProducts)
                                .andRoute(GET("/events"), handler::getProductEvents)
                                .andNest(path("/{id}"),
                                        route(method(HttpMethod.GET), handler::getProduct)
                                                .andRoute(method(HttpMethod.PUT), handler::updateProduct)
                                                .andRoute(method(HttpMethod.DELETE), handler::deleteProduct)
                                        )
                        )
                );
    }
	
    @Bean
    public RouterFunction<ServerResponse> routerFunction() {

        return route()
                .add(route01())
                .build();
    }
}
