package ru.yandex.workshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.yandex.workshop.model.UserInDto;
import ru.yandex.workshop.model.UserOutDto;
import ru.yandex.workshop.model.UserPatchDto;
import ru.yandex.workshop.service.UserService;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class UserControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private UserService service;

    @Test
    @SneakyThrows
    void createUser_goodResult() {
        UserInDto userInDto = UserInDto.builder().name("Иван")
                .email("4@6.").password("Password")
                .aboutMe("Летаю высоко").build();
        UserOutDto userOutDto = UserOutDto.builder().id(1L).build();
        when(service.create(any())).thenReturn(userOutDto);
        String result = mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(mapper.writeValueAsString(userInDto)))
                .andExpect(status().is(201))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        assertEquals(mapper.writeValueAsString(userOutDto), result);

    }

    @Test
    @SneakyThrows
    void patchUser_goodResult() {
        UserPatchDto userPatchDto = UserPatchDto.builder().aboutMe("Новые данные").build();
        UserOutDto userOutDto = UserOutDto.builder().id(1L).build();
        when(service.patch(anyLong(), anyString(), any())).thenReturn(userOutDto);
        mvc.perform(patch("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .header("userId", 1L)
                .header("password", "password")
                .content(mapper.writeValueAsString(userPatchDto)))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void getUser_goodResult() {
        UserOutDto userOutDto = UserOutDto.builder().id(1L).build();
        when(service.getUserById(anyLong(), anyString(), anyLong())).thenReturn(userOutDto);
        mvc.perform(get("/users/1")
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON)
                .header("userId", 1L)
                .header("password", "password")).andExpect(status().isOk());
    }
}