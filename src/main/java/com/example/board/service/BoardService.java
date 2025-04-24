package com.example.board.service;

import com.example.board.dto.BoardDto;
import com.example.board.entity.Board;
import com.example.board.mapper.BoardMapper;
import com.example.board.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardMapper boardMapper;

    // 게시판 리스트
    public List<BoardDto> boardList(){
       List<Board> boards = boardRepository.findAll();
       List<BoardDto> boardDtos = boardMapper.toDtoList(boards);

       return boardDtos;
    }

    // 게시판 상세보기
    public BoardDto show(Long idx){
        Board board = boardRepository.findById(idx).get();
        BoardDto boardDto = boardMapper.toDto(board);

        return boardDto;
    }

    // 게시글 작성
    public Board create(BoardDto boardDto) {
        // 1. dto -> entity로 변환
        Board board = boardMapper.toEntity(boardDto);
        Board saved = boardRepository.save(board);

        return saved;
    }

    // 게시글 수정
    public void update(BoardDto boardDto){
        // 1. dto -> entity로 변환(수정 dto를 entity로 저장)
        Board board = boardMapper.toEntity(boardDto);
        log.info(board.toString());

        boardRepository.save(board);
    }

    // 게시글 삭제
    public  void delete(BoardDto boardDto) {
        // dto -> entity
        Board board = boardMapper.toEntity(boardDto);
        boardRepository.deleteById(board.getIdx());
    }
}
