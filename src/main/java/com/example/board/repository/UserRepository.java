package com.example.board.repository;

import com.example.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // 회원가입 시 동일한 id를 가진 회원이 있는지
    boolean existsByUserId(String userId);

    User findByUserId(String userId);
}
