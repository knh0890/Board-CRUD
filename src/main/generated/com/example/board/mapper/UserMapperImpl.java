package com.example.board.mapper;

import com.example.board.dto.UserDto;
import com.example.board.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-13T17:38:31+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setIdx( user.getIdx() );
        userDto.setUserId( user.getUserId() );
        userDto.setPass( user.getPass() );
        userDto.setEmail( user.getEmail() );
        userDto.setCreateDate( user.getCreateDate() );
        userDto.setModifieDate( user.getModifieDate() );

        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.idx( userDto.getIdx() );
        user.userId( userDto.getUserId() );
        user.pass( userDto.getPass() );
        user.email( userDto.getEmail() );

        user.role( com.example.board.role.Role.ROLE_USER );

        return user.build();
    }

    @Override
    public List<UserDto> toDtoList(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( users.size() );
        for ( User user : users ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public List<User> toEntityList(List<UserDto> userDtos) {
        if ( userDtos == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userDtos.size() );
        for ( UserDto userDto : userDtos ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }
}
