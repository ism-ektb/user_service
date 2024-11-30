package ru.yandex.workshop.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.yandex.workshop.exception.exception.BaseRelationshipException;
import ru.yandex.workshop.model.UserInDto;
import ru.yandex.workshop.model.UserOutDto;
import ru.yandex.workshop.model.UserPatchDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceImplTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:15");
    @Autowired
    private UserService service;

    @Test
    @Order(1)
    void create() {
        UserInDto userInDto = UserInDto.builder()
                .name("Ivan1")
                .aboutMe("Good")
                .password("password")
                .email("rrr@hhh.ru")
                .build();
        UserOutDto userOutDto = service.create(userInDto);
        assertEquals(userOutDto.getId(), 2L);
    }

    @Test
    @Order(2)
    void create_bad() {
        UserInDto userInDto = UserInDto.builder()
                .name("Ivan1")
                .aboutMe("Good")
                .password("password")
                .email("rrr@hhh.ru")
                .build();
        assertThrows(DataIntegrityViolationException.class, () -> service.create(userInDto));

    }

    @Test
    @Order(3)
    void patch() {
        UserPatchDto userPatchDto = UserPatchDto.builder()
                .name("newname").build();
        UserOutDto userOutDto = service.patch(2L, "password", userPatchDto);
        assertEquals(userOutDto.getId(), 2L);
    }

    @Test
    @Order(4)
    void patch_badPassword() {
        UserPatchDto userPatchDto = UserPatchDto.builder()
                .name("newname").build();
        assertThrows(BaseRelationshipException.class, () -> service.patch(2L, "password1", userPatchDto));

    }

    @Test
    @Order(5)
    void getUserById() {
        UserOutDto userOutDto = service.getUserById(2L, "password", 2L);
        assertEquals(userOutDto.getPassword(), "password");
    }

    @Test
    @Order(6)
    void getUserById_otherId() {
        UserOutDto userOutDto = service.getUserById(1L, "password", 2L);
        assertEquals(userOutDto.getPassword(), null);
    }

    @Test
    void getAllUser() {
        List<UserOutDto> list = service.getAllUser(PageRequest.of(0, 10));
        assertEquals(list.size(), 2);
    }

    @Test
    void deleteUser() {
        service.deleteUser(1L, "password");
    }
}