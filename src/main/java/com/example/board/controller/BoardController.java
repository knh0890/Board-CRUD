package com.example.board.controller;

import com.example.board.dto.BoardDto;
import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import com.example.board.service.BoardService;
import com.example.board.service.CommentService;
import com.example.board.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

@Controller
@Slf4j
public class BoardController {

    @Autowired
    private  BoardService boardService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    // 게시판 리스트
    @GetMapping("/")
    public String main(Model model,
                       @RequestParam(required = false) String searchField, // @RequestParam(required = false): 선택적이 되며, 파라미터가 없으면 null이 전달
                       @RequestParam(required = false) String searchWord,
                       @RequestParam(defaultValue = "1") int page,
                       @PageableDefault(size = 5, sort = "idx", direction = Sort.Direction.DESC)Pageable pageable){

        // page가 1부터 오기 때문에 pageable에 1을 빼서 넘겨줍니다.
        // 페이지 번호가 1 미만이면 1로 처리
        if (page < 1) {
            page = 1;
        }
        Page<BoardDto> boardPage = boardService.boardList(PageRequest.of(page - 1, pageable.getPageSize(), pageable.getSort()), searchField, searchWord);
        model.addAttribute("lists", boardPage.getContent()); // 현재 페이지 데이터
        model.addAttribute("page", boardPage); // 페이지에 대한 전체 정보

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
        // 글 클릭 시 조회수 증가
        boardService.viewCount(idx);

        model.addAttribute("board", boardService.show(idx));
        // 댓글 보여주기
        model.addAttribute("comments", commentService.commentShow(idx));

        System.out.println(commentService.commentShow(idx));

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
