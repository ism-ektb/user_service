package ru.yandex.workshop.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class UserOutDto {

    @Schema(description = "id", example = "42")
    Long id;
    @Schema(description = "Имя", example = "Вова")
    String name;
    @Schema(description = "Электронная почта, отправляется только самому себе", example = "q@1.1")
    String email;
    @Schema(description = "Пароль, , отправляется только самому себе", example = "password")
    @Size(min = 3, max = 50, message = "Пароль пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Пароль пользователя не может быть пустыми")
    String password;
    @Schema(description = "Описание", example = "Очень крутой")
    String aboutMe;
}
