package ru.yandex.workshop.model.mapper;

import org.mapstruct.Mapper;
import ru.yandex.workshop.model.User;
import ru.yandex.workshop.model.UserOutDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = AllUserMapper.class)
public interface UserListMapper {

    List<UserOutDto> modelsToDtos(List<User> list);
}
