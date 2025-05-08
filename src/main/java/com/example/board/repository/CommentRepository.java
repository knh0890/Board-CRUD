package com.example.board.repository;

import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoard(Board board);

    Optional<Comment> findByIdxAndBoard_Idx(Long id, Long boardId);
}
