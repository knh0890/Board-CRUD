package com.example.board.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDto {

    private Long idx; // 댓글 인덱스
    private String id; // 작성자
    private String content; // 댓글 내용
    private Long boardIdx; // 게시글 인덱스
    private LocalDateTime creDate; // 작성일
    private LocalDateTime modifieDate; // 수정일
}
