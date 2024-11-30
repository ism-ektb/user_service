package ru.yandex.workshop.model.mapper;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.yandex.workshop.model.User;
import ru.yandex.workshop.model.UserOutDto;


@SpringBootTest
@Testcontainers
class UserMapperTest {

    @Autowired
    private  UserMapper mapper;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:15");

    @Test
    void modelToDtoForSelf() {
        User user = User.builder().name("Иван").email("2@2.2").password("password").build();
        UserOutDto userOutDto = UserOutDto.builder().name("Иван").email("2@2.2").password("password").build();
        Assertions.assertEquals(mapper.modelToDtoForSelf(user), userOutDto);
    }

    @Test
    void modelToDto() {
        User user = User.builder().name("Иван").email("2@2.2").password("password").build();
        UserOutDto userOutDto = UserOutDto.builder().name("Иван").build();
        Assertions.assertEquals(mapper.modelToDto(user), userOutDto);
    }
}