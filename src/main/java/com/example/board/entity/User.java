package com.example.board.entity;

import com.example.board.role.Role;
import com.example.board.time.Time;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx; // 회원 인덱스
    @Column(nullable = false, unique = true, length = 20)
    private String userId; // 아이디
    @Column(nullable = false)
    private String pass; // 비밀번호
    @Column(nullable = false)
    private String email; // 이메일

    @Enumerated(EnumType.STRING)
    private Role role; // 권한
    // 작성일(=가입일)
    // 수정일
}
