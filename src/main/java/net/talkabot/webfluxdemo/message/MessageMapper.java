package net.talkabot.webfluxdemo.message;

import java.util.Optional;

public interface MessageMapper {

    static Message toDto(MessageEntity entity) {
        return Message.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .content(entity.getContent())
                .sent(entity.getSent())
                .build();
    }

    static MessageEntity toEntity(Message dto) {
        MessageEntity entity = new MessageEntity();
        entity.setId(dto.getId());
        entity.setUserId(dto.getUserId());
        entity.setContent(dto.getContent());
        Optional.ofNullable(dto.getSent())
                .ifPresent(entity::setSent);
        return entity;
    }
}
