package net.talkabot.webfluxdemo.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepository repository;
    private final MessageStream messageStream;

    public Flux<Message> getByUserId(Long userId) {
        return repository.getLast20ByUserIdOrderBySentAsc(userId)
                .map(MessageMapper::toDto);
    }

    public Mono<Message> save(Long userId, Mono<Message> message) {
        Flux<MessageEntity> entities = Flux.from(message)
                .doOnNext(m -> m.setUserId(userId))
                .map(MessageMapper::toEntity);
        return repository
                .saveAll(entities)
                .next()
                .map(MessageMapper::toDto)
                .doOnNext(messageStream::next);
    }

    public Flux<Message> streamByUserId(Long userId) {
        Flux<Message> userMessageStream = messageStream.stream()
                .filter(message -> message.getUserId().equals(userId));
        return getByUserId(userId)
                .concatWith(userMessageStream);
    }

}
