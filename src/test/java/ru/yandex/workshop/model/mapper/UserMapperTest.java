package ru.yandex.workshop.model.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.workshop.model.User;
import ru.yandex.workshop.model.UserOutDto;


class UserMapperTest {


    private final UserMapper mapper = new UserMapperImpl();


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