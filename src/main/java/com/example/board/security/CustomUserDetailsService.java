package com.example.board.security;

import com.example.board.security.CustomUserDetails;
import com.example.board.entity.User;
import com.example.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // 로그인 인증 시 사용
public class CustomUserDetailsService implements UserDetailsService {

    // CustomUserDetailsService 클래스 역할
    // - UserDetailsService 인터페이스를 구현한 클래스
    // - 로그인 시 아이디를 받아서 DB에서 사용자 정보를 조회하고,
    //   이를 UserDetails 구현체 (CustomUserDetails)로 감싸서 반환

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        User userData = userRepository.findByUserId(userId);

        if (userData == null) {
            throw new UsernameNotFoundException("해당 사용자 없음: " + userId);
        }

        return  new CustomUserDetails(userData);
    }
}
