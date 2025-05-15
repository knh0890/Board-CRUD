package com.example.board.entity;

import com.example.board.dto.CommentDto;
import com.example.board.time.Time;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder(toBuilder = true)
public class Comment extends Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // // 1,2,3, 자동 생성
    private  Long idx; // 댓글 인덱스
    @Column(nullable = false)
    private String id; // 작성자
    @Column(nullable = false)
    private String content; // 내용
    @ManyToOne
    @JoinColumn(name = "board_idx", nullable = false)
    private Board board; // 어떤 게시글의 댓글인지 연결
    // 작성일
    // 수정일

    public void patch(CommentDto commentDto) {
        // 예외 발생
        if (commentDto.getIdx() == null || !this.idx.equals(commentDto.getIdx())) {
            throw new IllegalArgumentException("댓글 수정 실패!");
        }

        // 객체를 갱신
        if (commentDto.getContent() != null) {
            this.content = commentDto.getContent();
        }
    }
}
