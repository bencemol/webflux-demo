package net.talkabot.webfluxdemo.message;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface MessageRepository extends ReactiveCrudRepository<MessageEntity, Long> {

    @Query("select * from (select top 20 * from Message m where m.user_id = :userId " +
            "order by sent desc) order by sent asc")
    Flux<MessageEntity> getLast20ByUserIdOrderBySentAsc(Long userId);
}
