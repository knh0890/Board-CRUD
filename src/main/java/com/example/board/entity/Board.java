package com.example.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1,2,3, 자동 생성
    private Long idx; // 글 인덱스

    @Column
    private String id; // 작성자
    @Column
    private String title; // 제목
    @Column
    private String content; // 내용
    @Column
    private int count; // 조회수

}
