package com.example.board.entity;

import com.example.board.time.Time;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder(toBuilder = true)
public class Board extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1,2,3, 자동 생성
    private Long idx; // 글 인덱스
    @Column(nullable = false)
    private String id; // 작성자
    @Column(nullable = false)
    private String title; // 제목
    @Column(nullable = false)
    private String content; // 내용

//    public Board(String id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }

}
