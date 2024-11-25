package ru.yandex.workshop.model.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.workshop.model.User;
import ru.yandex.workshop.model.UserOutDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Microsoft)"
)
@Component
public class UserListMapperImpl implements UserListMapper {

    @Autowired
    private AllUserMapper allUserMapper;

    @Override
    public List<UserOutDto> modelsToDtos(List<User> list) {
        if ( list == null ) {
            return null;
        }

        List<UserOutDto> list1 = new ArrayList<UserOutDto>( list.size() );
        for ( User user : list ) {
            list1.add( allUserMapper.modelToDto( user ) );
        }

        return list1;
    }
}
