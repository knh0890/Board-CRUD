package com.example.board.mapper;

import com.example.board.dto.BoardDto;
import com.example.board.entity.Board;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring") // Spring이 빈으로 관리하도록 설정
public interface BoardMapper {

    BoardDto toDto(Board board);

//    @Mapping(target = "idx", ignore = true) // 글 작성시 idx 무시하고 생성하기 위해 사용 했으나 필요 없음
    Board toEntity(BoardDto boardDto);

    List<BoardDto> toDtoList(List<Board> boards);

    List<Board> toEntityList(List<BoardDto> boardDtos);
}
