package net.talkabot.webfluxdemo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @GetMapping("")
    public Flux<User> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<User> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Flux<User> create(@RequestBody Flux<User> users) {
        return service.create(users);
    }

    @GetMapping(value = "/live", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> stream() {
        return service.findAll();
    }
}
