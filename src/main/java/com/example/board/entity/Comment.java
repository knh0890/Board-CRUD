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
}
