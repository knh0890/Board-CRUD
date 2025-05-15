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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CommentService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    @Transactional
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
        log.info("✅ 댓글 저장됨: {}", save);
        log.info("🕒 createDate: {}", save.getCreateDate());

        CommentDto dto = commentMapper.toDto(save);

        return dto;
    }

    // 댓글 보여주기
    public List<CommentDto> commentShow(Long boardIdx){
        Board board = boardRepository.findById(boardIdx)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다" + boardIdx));

        List<Comment> comments = commentRepository.findByBoard(board); // ← 게시글에 달린 댓글들
        List<CommentDto> dtos = commentMapper.toDtoList(comments);

//        log.info("변환된 댓글 리스트: {}", dtos);

        return dtos;
    }

    @Transactional
    // 댓글 수정
    public CommentDto commentUpdate(Long boardIdx, Long commentIdx, CommentDto commentDto) {
        Comment target = commentRepository.findByIdxAndBoard_Idx(commentIdx, boardIdx)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않음"));

        // 댓글 수정
        target.patch(commentDto);

        // DB로 갱신
        Comment updated = commentRepository.save(target);

        // 댓글 엔티티를 DTO로 변환 및 반환
        CommentDto dto = commentMapper.toDto(updated);
        return dto;
    }

    @Transactional
    // 댓글 삭제
    public void commentDelete(Long commentIdx) {
        Comment target = commentRepository.findById(commentIdx)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패!"));

        commentRepository.delete(target);


    }
}
