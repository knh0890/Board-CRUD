package com.example.board.service;

import com.example.board.dto.UserDto;
import com.example.board.entity.User;
import com.example.board.mapper.UserMapper;
import com.example.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String signup(UserDto userDto){

        // db에 이미 동일한 id가 가진 회원이 존재하는지 검증?
        boolean isUser = userRepository.existsByUserId(userDto.getUserId());
        if (isUser){
            throw new RuntimeException("이미 존재하는 회원 ID입니다.");
        }

        System.out.println(userDto.getPass());
        System.out.println(userDto.getPassconfirm());

        // 1. DTO → Entity 변환
        User user = userMapper.toEntity(userDto);

        // 2. 비밀번호 암호화
        User encodedUser = User.builder()
                .userId(user.getUserId())
                .pass(bCryptPasswordEncoder.encode(user.getPass()))
                .email(user.getEmail())
                .role(user.getRole())
                .build();

        // 3. 저장
        userRepository.save(encodedUser);

        return "회원가입 성공";
    }
}
