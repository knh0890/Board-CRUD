package com.example.board.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class BoardController {

    @GetMapping("/board")
    public String hello(){
        return "hello world!";
    }
}
