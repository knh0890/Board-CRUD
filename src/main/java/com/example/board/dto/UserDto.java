package com.example.board.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@ToString
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long idx; // 회원 인덱스
    private String userId; // 아이디
    private String pass;  // 비밀번호
    private String passconfirm; // 비밀번호 확인
    private String email; // 이메일
    private LocalDateTime createDate; // 회원 가입일
    private LocalDateTime modifieDate; // 회원 정보 수정일
    private String role; // 회원 권한
}
