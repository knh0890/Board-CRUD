package com.example.board.role;

public enum Role {
    // String으로 하면 오타, 대소문자 문제 생김.
    // Enum이면 컴파일 시점에서 오류 잡을 수 있음 => 다른값이 들어갈 수 없음
    // 일관성 유지, 가독성 향상, 권한 늘어도 Enum에만 추가 하면 됨

    ROLE_USER,
    ROLE_ADMIN
}
