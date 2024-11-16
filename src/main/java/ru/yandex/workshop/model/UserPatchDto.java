package ru.yandex.workshop.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Schema(name = "Запрос от изменение сведений о пользователе")
@Getter
@Builder
public class UserPatchDto {

    @Schema(description = "Новое Имя", example = "Вова")
    @Size(min = 3, max = 50, message = "Имя пользователя должно содержать от 3 до 50 символов")
    String name;
    @Schema(description = "Новый пароль", example = "password")
    @Size(min = 3, max = 50, message = "Пароль пользователя должно содержать от 5 до 50 символов")
    String password;
    @Schema(description = "Новое описание", example = "Очень крутой")
    @Size(min = 5, max = 200, message = "Пароль пользователя должно содержать от 5 до 200 символов")
    String aboutMe;
}
