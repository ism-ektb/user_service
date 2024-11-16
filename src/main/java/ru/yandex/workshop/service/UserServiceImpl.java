package ru.yandex.workshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.yandex.workshop.exception.exception.BaseRelationshipException;
import ru.yandex.workshop.exception.exception.NoFoundObjectException;
import ru.yandex.workshop.model.User;
import ru.yandex.workshop.model.UserInDto;
import ru.yandex.workshop.model.UserOutDto;
import ru.yandex.workshop.model.UserPatchDto;
import ru.yandex.workshop.model.mapper.PatchMapper;
import ru.yandex.workshop.model.mapper.UserListMapper;
import ru.yandex.workshop.model.mapper.UserMapper;
import ru.yandex.workshop.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PatchMapper patchMapper;
    private final UserListMapper listMapper;

    /**
     * Создание новоо пользователя
     *
     * @param userInDto
     */
    @Override
    public UserOutDto create(UserInDto userInDto) {
        User user = repository.save(mapper.dtoToModel(userInDto));
        return mapper.modelToDtoForSelf(user);
    }

    /**
     * Изменение данных о пользователе
     *
     * @param userId
     * @param password
     * @param userPatchDto
     */
    @Override
    public UserOutDto patch(Long userId, String password, UserPatchDto userPatchDto) {
        User user = isValidUser(userId, password);
        User newUser = patchMapper.patch(user, userPatchDto);
        return mapper.modelToDtoForSelf(repository.save(newUser));
    }

    /**
     * Получение данных о пользователе, пароль и емайл сообщаются только самому пользователю
     *
     * @param userId
     * @param password
     * @param id
     */
    @Override
    public UserOutDto getUserById(Long userId, String password, Long id) {
        User user = isValidUser(userId, password);
        if (user.getId() == id) return mapper.modelToDtoForSelf(user);
        else {
            User findUser = repository.findById(id).orElseThrow(() -> new NoFoundObjectException(
                    String.format("Пользователь с id {} не найден", id)));
            return mapper.modelToDto(findUser);
        }

    }

    /**
     * Получение списка всех пользователей, пароль не сообщается
     *
     * @param pageRequest
     */
    @Override
    public List<UserOutDto> getAllUser(PageRequest pageRequest) {
        Page<User> users = repository.findAll(pageRequest);
        return listMapper.modelsToDtos(users.getContent());
    }

    /**
     * Удаление данных о пользователе
     *
     * @param userId
     * @param password
     */
    @Override
    public void deleteUser(Long userId, String password) {
        isValidUser(userId, password);
        repository.deleteById(userId);
    }

    /**
     * Проверка вкодных данных на валидность
     * @param userId
     * @param password
     * @return
     */
    private User isValidUser(Long userId, String password) {
        User user = repository.findById(userId).orElseThrow(() ->
                new NoFoundObjectException(String.format("Пользователя с Id %ы не существует", userId)));
        if (!(user.getPassword().equals(password))) new BaseRelationshipException(
                String.format("Пароль пользователя c Id %s не верен", userId));
        return user;
    }
}
