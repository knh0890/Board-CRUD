package com.example.board.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // 로그인 페이지 이동
    @GetMapping("/boards/login")
    public String loginForm(){
        return "boards/login";
    }

}
