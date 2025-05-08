package com.example.board.controller;

import com.example.board.dto.CommentDto;
import com.example.board.entity.Comment;
import com.example.board.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
    public class CommentController {

    @Autowired
    private CommentService commentService;

    // 댓글 등록
    @PostMapping("/boards/{boardIdx}/comments")
    @ResponseBody // 일반 Controller에서도 JSON 리턴 가능하게 해줌
    public ResponseEntity<CommentDto> commentCreate(@PathVariable Long boardIdx,
                                    @RequestBody CommentDto commentDto) {
        CommentDto dto = commentService.commentCreate(boardIdx, commentDto);
//        return dto;
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    // 댓글 수정
    @PatchMapping("/boards/{boardIdx}/comments/{commentIdx}")
    @ResponseBody
    public ResponseEntity<CommentDto> commentEdit(@PathVariable Long boardIdx,
                                                  @PathVariable Long commentIdx,
                                                  @RequestBody CommentDto commentDto) {
        commentService.commentUpdate(boardIdx, commentIdx, commentDto);

        return ResponseEntity.ok().build();
    }

}
