package com.apirest.webflux.router;

import com.apirest.webflux.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> route(UserHandler userHandler) {
        return RouterFunctions
                .route(GET("/users").and(accept(MediaType.APPLICATION_JSON)), userHandler::findAll)
                .andRoute(GET("/users/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::findById)
                .andRoute(POST("/users").and(accept(MediaType.APPLICATION_JSON)), userHandler::create)
                .andRoute(DELETE("/users/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::deleteById);
    }
}
