package net.talkabot.webfluxdemo.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    public Flux<User> findAll() {
        return repository.findAll()
                .map(UserMapper::toDto);
    }

    public Mono<User> findById(Long id) {
        return repository.findById(id)
                .map(UserMapper::toDto);
    }

    public Flux<User> create(Flux<User> users) {
        return repository
                .saveAll(users.map(UserMapper::toEntity))
                .map(UserMapper::toDto);
    }
}

