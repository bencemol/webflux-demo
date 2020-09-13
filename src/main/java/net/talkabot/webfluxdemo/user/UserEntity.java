package net.talkabot.webfluxdemo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("user")
public class UserEntity {

    @Id
    private Long id;

    private String name;

    private LocalDateTime registered = LocalDateTime.now();
}
