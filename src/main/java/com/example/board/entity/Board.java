package com.example.board.entity;

import com.example.board.time.Time;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor // 파라미터 없는 생성자 자동 생성
@AllArgsConstructor // 모든 필드를 포함하는 생성자 자동 생성
@Getter // 모든 필드의 getter 메서드 자동 생성
@ToString // toString() 자동 생성 (디버깅에 유용)
@Builder(toBuilder = true) // 빌더 패턴 자동 생성 + 기존 객체 기반으로 빌더 생성 가능
public class Board extends Time {

    @Id // 기본키(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1,2,3, 자동 생성
    private Long idx; // 글 인덱스
    @Column(nullable = false)
    private String id; // 작성자
    @Column(nullable = false)
    private String title; // 제목
    @Column(nullable = false)
    private String content; // 내용
    @Column(nullable = false)
    private int viewCount; // 조회수
    // 작성일
    // 수정일

    public void update(Board updated) {
        this.title = updated.getTitle();
        this.content = updated.content;
    }

}
