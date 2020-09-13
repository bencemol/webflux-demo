package net.talkabot.webfluxdemo.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class SpamTest {

    private final WebClient client = WebClient.create("http://localhost:8080/chat/api");

    @Scheduled(fixedDelay = 10)
    public void spam() {
        Message message = Message.builder()
                .content("ping")
                .build();
        WebClient.RequestHeadersSpec<?> spamRequest = client
                .method(HttpMethod.POST)
                .uri("/users/2/messages")
                .body(BodyInserters.fromPublisher(Mono.just(message), Message.class));
        spamRequest.retrieve()
                .bodyToMono(Message.class)
                .subscribe();
    }
}
