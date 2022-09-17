package com.apirest.webflux.service;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.repository.PlaylistRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public Mono<Playlist> createPlaylist(Playlist playlist) {
        log.info("Creating playlist: ", playlist);
        return playlistRepository.save(playlist);
    }

    public Mono<Playlist> findById(final String id) {
        log.debug("Search playlist by id: ", id);
        return playlistRepository.findById(id);
    }

    public Flux<Playlist> getAll() {
        log.info("Searching all playlists");
        return playlistRepository.findAll();
    }

    public Mono<Playlist> deleteById(final String id) {
        return findById(id).flatMap(
                playlist -> playlistRepository.delete(playlist).then(Mono.just(playlist)));
    }
}
