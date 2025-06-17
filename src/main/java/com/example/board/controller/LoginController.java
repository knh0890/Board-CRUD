package com.example.board.controller;

import com.example.board.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    // 로그인 페이지 이동
    @GetMapping("/login")
    public String loginForm(HttpServletRequest request,
                            @RequestParam(value = "error", required = false) String error,
                            Model model,
                            UserDto userDto){

        if (error != null) {
            model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
        }

        // CSRF 토큰 모델에 수동 주입
        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
        if (csrfToken != null) {
            model.addAttribute("_csrf", csrfToken);
        }

        return "boards/login";
    }

//    @GetMapping("/login")
//    public String loginForm(){
//        return "boards/login";
//    }

}
