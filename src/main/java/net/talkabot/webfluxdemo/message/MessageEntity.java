package net.talkabot.webfluxdemo.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("message")
public class MessageEntity {

    @Id
    private Long id;

    private Long userId;

    private String content;

    private LocalDateTime sent = LocalDateTime.now();
}
