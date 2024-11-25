package ru.yandex.workshop.model.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.yandex.workshop.model.User;
import ru.yandex.workshop.model.UserInDto;
import ru.yandex.workshop.model.UserOutDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User dtoToModel(UserInDto userInDto) {
        if ( userInDto == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.name( userInDto.getName() );
        user.email( userInDto.getEmail() );
        user.password( userInDto.getPassword() );
        user.aboutMe( userInDto.getAboutMe() );

        return user.build();
    }

    @Override
    public UserOutDto modelToDtoForSelf(User user) {
        if ( user == null ) {
            return null;
        }

        UserOutDto.UserOutDtoBuilder userOutDto = UserOutDto.builder();

        userOutDto.id( user.getId() );
        userOutDto.name( user.getName() );
        userOutDto.email( user.getEmail() );
        userOutDto.password( user.getPassword() );
        userOutDto.aboutMe( user.getAboutMe() );

        return userOutDto.build();
    }

    @Override
    public UserOutDto modelToDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserOutDto.UserOutDtoBuilder userOutDto = UserOutDto.builder();

        userOutDto.id( user.getId() );
        userOutDto.name( user.getName() );
        userOutDto.aboutMe( user.getAboutMe() );

        return userOutDto.build();
    }
}
