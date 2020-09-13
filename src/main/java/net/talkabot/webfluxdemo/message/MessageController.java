package net.talkabot.webfluxdemo.message;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users/{userId}/messages")
public class MessageController {

    private final MessageService service;

    @GetMapping("")
    public Flux<Message> getByUserId(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }

    @PostMapping("")
    public Mono<Message> send(@PathVariable Long userId, @RequestBody Mono<Message> message) {
        return service.save(userId, message);
    }

    @GetMapping("/live")
    public Flux<Message> streamByUserId(@PathVariable Long userId) {
        return service.streamByUserId(userId);
    }
}
