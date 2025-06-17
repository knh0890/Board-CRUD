package com.example.board.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    public Map<String, String> userInfo(){ // 현재 로그인한 사용자의 id와 role
        Map<String, String> result = new HashMap<>();

        // 로그인 후 현재 사용자 정보 조회
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // // 인증 정보가 없거나 인증되지 않았거나 익명 사용자일 경우 빈 값 반환
        if (authentication == null || !authentication.isAuthenticated() ||
                "anonymousUser".equals(authentication.getPrincipal())) {
            return result;
        }

        // 세션 현재 사용자 아이디
        String id = authentication.getName();
        result.put("id", id);

        // 세션 현재 사용자 role
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = "";
        if (!authorities.isEmpty()) {
            role = authorities.iterator().next().getAuthority();
        }
        result.put("role", role);

        return result;
    }
}
