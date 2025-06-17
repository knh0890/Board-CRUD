package com.example.board.mapper;

import com.example.board.dto.BoardDto;
import com.example.board.entity.Board;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-12T14:14:40+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class BoardMapperImpl implements BoardMapper {

    @Override
    public BoardDto toDto(Board board) {
        if ( board == null ) {
            return null;
        }

        BoardDto boardDto = new BoardDto();

        boardDto.setIdx( board.getIdx() );
        boardDto.setId( board.getId() );
        boardDto.setTitle( board.getTitle() );
        boardDto.setContent( board.getContent() );
        boardDto.setCreateDate( board.getCreateDate() );
        boardDto.setModifieDate( board.getModifieDate() );
        boardDto.setViewCount( board.getViewCount() );

        return boardDto;
    }

    @Override
    public Board toEntity(BoardDto boardDto) {
        if ( boardDto == null ) {
            return null;
        }

        Board.BoardBuilder board = Board.builder();

        board.idx( boardDto.getIdx() );
        board.id( boardDto.getId() );
        board.title( boardDto.getTitle() );
        board.content( boardDto.getContent() );
        board.viewCount( boardDto.getViewCount() );

        return board.build();
    }

    @Override
    public List<BoardDto> toDtoList(List<Board> boards) {
        if ( boards == null ) {
            return null;
        }

        List<BoardDto> list = new ArrayList<BoardDto>( boards.size() );
        for ( Board board : boards ) {
            list.add( toDto( board ) );
        }

        return list;
    }

    @Override
    public List<Board> toEntityList(List<BoardDto> boardDtos) {
        if ( boardDtos == null ) {
            return null;
        }

        List<Board> list = new ArrayList<Board>( boardDtos.size() );
        for ( BoardDto boardDto : boardDtos ) {
            list.add( toEntity( boardDto ) );
        }

        return list;
    }
}
