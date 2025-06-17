package com.example.board.security;

import com.example.board.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    // CustomUserDetails 클래스 역할
    // - UserDetails 인터페이스를 구현한 클래스
    // - 스프링 시큐리티가 로그인 인증할 때 사용자 정보를 담은 객체로 활용

    private final User user;
    public CustomUserDetails(User user){
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // User의 role 필드를 권한 객체로 변환해서 리턴
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPass(); // DB 저장된 암호화된 비밀번호
    }

    @Override
    public String getUsername() {
        return user.getUserId(); // 로그인 아이디
    }
}
