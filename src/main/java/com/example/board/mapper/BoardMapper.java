package com.example.board.mapper;

import com.example.board.dto.BoardDto;
import com.example.board.entity.Board;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring") // Spring이 빈으로 관리하도록 설정
public interface BoardMapper {

    BoardDto toDto(Board board);

    Board toEntity(BoardDto boardDto);

    List<BoardDto> toDtoList(List<Board> boards);

    List<Board> toEntityList(List<BoardDto> boardDtos);
}
