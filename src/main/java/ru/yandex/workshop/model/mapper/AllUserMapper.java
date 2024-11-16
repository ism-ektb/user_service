package ru.yandex.workshop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.yandex.workshop.model.User;
import ru.yandex.workshop.model.UserOutDto;

@Mapper(componentModel = "spring")
public interface AllUserMapper {
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserOutDto modelToDto(User user);
}
