package ru.yandex.workshop.model.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.workshop.model.User;
import ru.yandex.workshop.model.UserPatchDto;

@Component
public class PatchMapper {
    public User patch(User user, UserPatchDto userPatchDto) {
        User newUser = User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(userPatchDto.getName() != null ? userPatchDto.getName() : user.getName())
                .password(userPatchDto.getPassword() != null ? userPatchDto.getPassword() : user.getPassword())
                .aboutMe(userPatchDto.getAboutMe() != null ? userPatchDto.getAboutMe() : user.getAboutMe())
                .build();

        return newUser;
    }
}
