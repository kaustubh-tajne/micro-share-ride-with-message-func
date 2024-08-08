package com.hcl.profilebooke.mapper;

import com.hcl.profilebooke.dto.CommentDto;
import com.hcl.profilebooke.model.Comment;

import java.util.List;
import java.util.stream.Collectors;

public class CommentMapper {

    public static List<CommentDto> toDto(List<Comment> commentList) {
        return commentList.stream()
                .map(comment -> toDto(comment))
                .collect(Collectors.toList());
    }

    public static CommentDto toDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setPostId(comment.getPost().getId());
        return commentDto;
    }

    public static List<Comment> toEntity(List<CommentDto> commentDtoList) {
        return commentDtoList.stream()
                .map(commentDto -> toEntity(commentDto))
                .collect(Collectors.toList());
    }

    public static Comment toEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setContent(commentDto.getContent());
        return comment;
    }



}
