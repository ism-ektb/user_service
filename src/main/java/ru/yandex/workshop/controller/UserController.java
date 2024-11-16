package ru.yandex.workshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.workshop.model.UserInDto;
import ru.yandex.workshop.model.UserOutDto;
import ru.yandex.workshop.model.UserPatchDto;
import ru.yandex.workshop.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "Работа с учетной записью")
@Slf4j
public class UserController {

    private final UserService service;

    @PostMapping
    @Operation(description = "Создание нового пользователя")
    @ResponseStatus(HttpStatus.CREATED)
    UserOutDto createUser(@RequestBody @Valid UserInDto userInDto) {
        UserOutDto userOutDto = service.create(userInDto);
        log.info("Создан пользователь и Id= {}", userOutDto.getId());
        return userOutDto;
    }

    @PatchMapping
    @Operation(description = "Изменение данных пользователя")
    UserOutDto patchUser(@RequestHeader @Positive long userId,
                         @RequestHeader String password,
                         @RequestBody @Valid UserPatchDto userPatchDto) {
        UserOutDto userOutDto = service.patch(userId, password, userPatchDto);
        log.info("Данные пользователя с Id {} обновлены", userOutDto.getId());
        return userOutDto;
    }

    @GetMapping("/{id}")
    @Operation(description = "Получение данных о пользователе")
    UserOutDto getUser(@RequestHeader @Positive long userId,
                       @RequestHeader String password,
                       @PathVariable long id) {
        UserOutDto userOutDto = service.getUserById(userId, password, id);
        log.info("Получены данные о пользователе с id {}", userOutDto.getId());
        return userOutDto;
    }

    @GetMapping
    @Operation(description = "Получение списка всех пользователей")
    List<UserOutDto> getAllUser(@RequestParam(defaultValue = "0", required = false) int from,
                                @RequestParam(defaultValue = "10", required = false) int size) {
        List<UserOutDto> list = service.getAllUser(PageRequest.of(from / size, size));
        log.info("Получен список пользователей");
        return list;
    }

    @DeleteMapping
    @Operation(description = "Удаление пользователя")
    void deleteUser(@RequestHeader @Positive long userId,
                    @RequestHeader String password) {
        service.deleteUser(userId, password);
        log.info("Пользователь с id = {} удален", userId);
    }
}
