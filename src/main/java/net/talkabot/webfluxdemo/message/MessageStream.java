package net.talkabot.webfluxdemo.message;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Component
public class MessageStream {

    private final DirectProcessor<Message> stream = DirectProcessor.create();
    private final FluxSink<Message> inputStream = stream.sink();

    @Bean
    public Flux<Message> stream() {
        return stream.publish().autoConnect();
    }

    public void next(Message message) {
        inputStream.next(message);
    }
}
