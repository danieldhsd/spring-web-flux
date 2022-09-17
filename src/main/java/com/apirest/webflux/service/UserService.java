package com.apirest.webflux.service;

import com.apirest.webflux.document.User;
import com.apirest.webflux.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Mono<User> createUser(User user) {
        log.info("Creating user: {}", user);
        return userRepository.save(user);
    }

    public Mono<User> findById(String id) {
        log.info("Finding user by id: {}", id);
        return userRepository.findById(id);
    }

    public Flux<User> findAll() {
        log.info("Finding all users");
        return userRepository.findAll();
    }

    public Mono<User> deleteById(String id) {
        log.info("Removing user by id: {}", id);
        return findById(id).flatMap(
                user -> userRepository.delete(user).then(Mono.just(user)));
    }
}

