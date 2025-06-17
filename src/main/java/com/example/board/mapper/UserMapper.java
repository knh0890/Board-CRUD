package com.example.board.mapper;

import com.example.board.dto.UserDto;
import com.example.board.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring") // Spring이 빈으로 관리하도록 설정
public interface UserMapper {

    @Mapping(target = "passconfirm", ignore = true) // Entity에 없는 필드 무시
    UserDto toDto(User user);

    @Mapping(target = "role", expression = "java(com.example.board.role.Role.ROLE_USER)") // 기본 권한 부여
    User toEntity(UserDto userDto);

    List<UserDto> toDtoList(List<User> users);

    List<User> toEntityList(List<UserDto> userDtos);
}
