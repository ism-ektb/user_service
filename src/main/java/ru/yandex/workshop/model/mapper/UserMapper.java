package ru.yandex.workshop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.yandex.workshop.model.User;
import ru.yandex.workshop.model.UserInDto;
import ru.yandex.workshop.model.UserOutDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User dtoToModel(UserInDto userInDto);
    UserOutDto modelToDtoForSelf(User user);

    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserOutDto modelToDto(User user);
}
