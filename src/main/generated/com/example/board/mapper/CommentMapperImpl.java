package com.example.board.mapper;

import com.example.board.dto.CommentDto;
import com.example.board.entity.Comment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-20T19:03:53+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentDto toDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDto commentDto = new CommentDto();

        commentDto.setIdx( comment.getIdx() );
        commentDto.setId( comment.getId() );
        commentDto.setContent( comment.getContent() );
        commentDto.setCreateDate( comment.getCreateDate() );
        commentDto.setModifieDate( comment.getModifieDate() );

        return commentDto;
    }

    @Override
    public Comment toEntity(CommentDto commentDto) {
        if ( commentDto == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.idx( commentDto.getIdx() );
        comment.id( commentDto.getId() );
        comment.content( commentDto.getContent() );

        return comment.build();
    }

    @Override
    public List<CommentDto> toDtoList(List<Comment> comments) {
        if ( comments == null ) {
            return null;
        }

        List<CommentDto> list = new ArrayList<CommentDto>( comments.size() );
        for ( Comment comment : comments ) {
            list.add( toDto( comment ) );
        }

        return list;
    }

    @Override
    public List<Comment> toEntityList(List<CommentDto> commentDtos) {
        if ( commentDtos == null ) {
            return null;
        }

        List<Comment> list = new ArrayList<Comment>( commentDtos.size() );
        for ( CommentDto commentDto : commentDtos ) {
            list.add( toEntity( commentDto ) );
        }

        return list;
    }
}
