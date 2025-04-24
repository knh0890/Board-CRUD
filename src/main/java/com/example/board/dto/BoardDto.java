package com.example.board.dto;

import com.example.board.entity.Board;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardDto {

    private Long idx; // 글 인덱스
    private String id; // 작성자
    private String title; // 제목
    private String content; // 내용


//    public BoardDto(String id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }
}
