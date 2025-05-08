package com.example.board.mapper;

import com.example.board.dto.CommentDto;
import com.example.board.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring") // Spring이 빈으로 관리하도록 설정
public interface CommentMapper {

    CommentDto toDto(Comment comment);

    Comment toEntity(CommentDto commentDto);

    List<CommentDto> toDtoList(List<Comment> comments);

    List<Comment> toEntityList(List<CommentDto> commentDtos);
}
