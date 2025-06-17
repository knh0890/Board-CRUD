package com.example.board.config;

import com.example.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;

@ControllerAdvice // Spring에서 전역적으로 모든 @Controller에 적용되는 공통 기능을 정의할 때 사용하는 어노테이션
public class GlobalModelAttribute { // 모든 페이지에서 헤더에 로그인한 사용자의 정보(아이디, 권한) 출력

    @Autowired
    private UserService userService;

    // @ModelAttribute가 붙은 메서드는 모든 컨트롤러의 요청 처리 전에 실행
    // 모든 뷰에 공통적으로 모델 데이터를 넣고 싶을 때 사용
    @ModelAttribute
    public void userInfo(Model model) { // 현재 로그인한 사용자의 id와 role

        Map<String, String> userInfo = userService.userInfo();

        // id와 role이 비어있지 않으면 모델에 추가
        if (userInfo.containsKey("id") && userInfo.containsKey("role")) {
            model.addAttribute("id", userInfo.get("id"));
            model.addAttribute("role", userInfo.get("role"));
        }
    }
}
