package ru.yandex.workshop.service;

import org.springframework.data.domain.PageRequest;
import ru.yandex.workshop.model.User;
import ru.yandex.workshop.model.UserInDto;
import ru.yandex.workshop.model.UserOutDto;
import ru.yandex.workshop.model.UserPatchDto;

import java.util.List;

public interface UserService {

    /**
     * Создание новоо пользователя
     */
    UserOutDto create(UserInDto userInDto);
    /**
     * Изменение данных о пользователе
     */
    UserOutDto patch(Long userId, String password, UserPatchDto userPatchDto);
    /**
     * Получение данных о пользователе, пароль и емайл сообщаются только самому пользователю
     */
    UserOutDto getUserById(Long userId, String password, Long id);
    /**
     * Получение списка всех пользователей, пароль не сообщается
     */
    List<UserOutDto> getAllUser(PageRequest pageRequest);
    /**
     * Удаление данных о пользователе
     */
    void deleteUser(Long userId, String password);
}
