package com.example.board.service;

import com.example.board.dto.CommentDto;
import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import com.example.board.mapper.CommentMapper;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommentService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    // 댓글 생성
    public CommentDto commentCreate(Long boardIdx, CommentDto commentDto) {
        // 게시글 조회 및 예외 발생
        Board board = boardRepository.findById(boardIdx)
               .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다" + boardIdx));

        // 댓글 엔티티 생성
        Comment comment = Comment.builder()
                .id(commentDto.getId())
                .content(commentDto.getContent())
                .board(board)
                .build();

        // 댓글 저장
        Comment save = commentRepository.save(comment);

        CommentDto dto = commentMapper.toDto(save);

        return dto;
    }
}
