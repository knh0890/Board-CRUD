package com.example.board.controller;

import com.example.board.dto.BoardDto;
import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import com.example.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class BoardController {

    @Autowired
    private  BoardService boardService;

    // 게시판 리스트
    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("lists", boardService.boardList());

        return "boards/home";
    }

    // 글 작성 페이지 이동
    @GetMapping("/boards/new")
    public String boardForm(){
        return "boards/new";
    }

    // 글 작성
    @PostMapping("/boards/create")
    public String createBoard(BoardDto boardDto){
        // DTO 로깅
        log.info(boardDto.toString());

        // DTO를 엔터티로 저장
        Board board = boardService.create(boardDto);
        // 엔터티 로깅
        log.info(board.toString());

        return "redirect:/boards/" + board.getIdx();
    }

    // 글 상세보기
    @GetMapping("/boards/{idx}")
    public String show(@PathVariable Long idx, Model model){
        model.addAttribute("board", boardService.show(idx));

        return "boards/show";
    }

    // 글 수정 페이지 이동
    @GetMapping("/boards/edit/{idx}")
    public String edit(@PathVariable Long idx, Model model){
        // 수정할 내용 들고 오기
        model.addAttribute("edit", boardService.show(idx));

        return "boards/edit";
    }

    // 글 수정
    @PostMapping("/boards/update")
    public String update(BoardDto boardDto){
        // 수정 내용 로깅
        log.info(boardDto.toString());

        boardService.update(boardDto);

        return "redirect:/boards/" + boardDto.getIdx();
    }

    // 글 삭제
    @PostMapping("/boards/delete")
    public String delete(BoardDto boardDto){
        boardService.delete(boardService.show(boardDto.getIdx()));

        return "redirect:/";
    }
}
