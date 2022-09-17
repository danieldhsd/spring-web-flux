package com.apirest.webflux.handler;

import com.apirest.webflux.document.User;
import com.apirest.webflux.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@AllArgsConstructor
public class UserHandler {

    private final UserService userService;

    public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.findAll(), User.class);
    }

    public Mono<ServerResponse> findById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.findById(id), User.class);
    }

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        final Mono<User> user = serverRequest.bodyToMono(User.class);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(user.flatMap(userService::createUser), User.class);
    }

    public Mono<ServerResponse> deleteById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.deleteById(id), User.class);
    }
}
