package net.talkabot.webfluxdemo.user;

import java.util.Optional;

public interface UserMapper {

    static User toDto(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .registered(entity.getRegistered())
                .build();
    }

    static UserEntity toEntity(User dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        Optional.ofNullable(dto.getRegistered())
                .ifPresent(entity::setRegistered);
        return entity;
    }
}
