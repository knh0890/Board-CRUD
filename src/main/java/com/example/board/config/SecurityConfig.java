package com.example.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 모든 요청에 대해 @Bean을 통해 등록된 SpringSecurity의 필터 체인을 거치게 함
public class SecurityConfig {


    @Bean // 비밀번호 암호화를 위한 Bean 등록
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean // 로그인 인증 시 사용할 AuthenticationManager 빈 등록
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean // 필터 체인 설정
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        // 경로별 접근 권한 설정
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/signup").permitAll() // permitAll() - 누구나 접근 가능
                .requestMatchers("/admin").hasRole("ADMIN") // ADMIN 권한만 접근 가능
                .requestMatchers("/boards/**").hasAnyRole("ADMIN", "USER") // ADMIN, USER 권한 접근 가능
                .anyRequest().authenticated() // authenticated() - 그 외 경로는 로그인한 사용자만 접근 가능
        );

        // 로그인 설정
        http.formLogin(login -> login
                .loginPage("/login") // 커스텀 로그인 페이지
                .loginProcessingUrl("/login") // 로그인 요청 처리 URL
                .usernameParameter("userId")
                .passwordParameter("pass")
                .defaultSuccessUrl("/", true) // 로그인 성공 시 "/"로 이동
                .permitAll() // 로그인 관련 경로는 모두 접근 허용
        );

        // 로그아웃 설정
        http.logout(logout -> logout
                .logoutUrl("/logout") // 로그아웃 처리 URL (기본값도 /logout)
                .logoutSuccessUrl("/") // 로그아웃 성공 후 이동 경로
                .invalidateHttpSession(true) // 세션 무효화
                .deleteCookies("JSESSIONID") // JSESSIONID 쿠키 삭제
                .permitAll()
        );

        // CSRF(Cross-Site Request Forgery)는 요청을 위조하여 사용자가 원하지 않아도 서버측으로 특정 요청을 강제로 보내는 방식
        // CSRF 보호 비활성화
        // http.csrf((auth) -> auth.disable());

        // 다중 로그인 설정
        http.sessionManagement(session  -> session
                .maximumSessions(1) // 하나의 아이디에 대한 다중 로그인 허용 개수
                .maxSessionsPreventsLogin(true) // 기존 세션이 있으면 새 로그인 차단
        );

        // 세션 고정 보호
        http.sessionManagement(session  -> session
                .sessionFixation()
                .changeSessionId() // changeSessionId() : 로그인 시 동일한 세션에 대한 id 변경(보안 강화)
        );

        return http.build();
    }
}
