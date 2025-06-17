package com.example.board.controller;

import com.example.board.dto.UserDto;
import com.example.board.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    @Autowired
    private SignupService signupService;

    // 회원가입 페이지 이동
    @GetMapping("/signup")
    public String signupForm(){

        return "boards/signup";
    }

    @PostMapping("/signup")
    public String signup(UserDto userDto){

        System.out.println(userDto.getUserId());

        signupService.signup(userDto);
        System.out.println("회원가입 성공");

        return "redirect:/login";
    }
}
