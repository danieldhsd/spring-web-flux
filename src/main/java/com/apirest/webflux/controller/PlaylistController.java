package com.apirest.webflux.controller;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.service.PlaylistService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    @PostMapping
    public Mono<Playlist> createPlaylist(@RequestBody final Playlist playlist) {
        return playlistService.createPlaylist(playlist);
    }

    @GetMapping("/{id}")
    public Mono<Playlist> getById(@PathVariable final String id) {
        return playlistService.findById(id);
    }

    @GetMapping
    public Flux<Playlist> getAll() {
        return playlistService.getAll();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Mono<Playlist> deleteById(@PathVariable final String id) {
        return playlistService.deleteById(id);
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Playlist>> getPlaylistsByEvents() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        Flux<Playlist> events = playlistService.getAll();
        log.info("Getting events");
        return Flux.zip(interval, events);
    }
}
