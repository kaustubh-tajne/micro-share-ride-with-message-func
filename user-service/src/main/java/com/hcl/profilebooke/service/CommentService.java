package com.hcl.profilebooke.service;

import com.hcl.profilebooke.dao.service.CommentDaoService;
import com.hcl.profilebooke.dao.service.PostDaoService;
import com.hcl.profilebooke.dto.CommentDto;
import com.hcl.profilebooke.mapper.CommentMapper;
import com.hcl.profilebooke.model.Comment;
import com.hcl.profilebooke.model.Post;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentDaoService commentDaoService;

    private final PostDaoService postDaoService;

    public CommentService(CommentDaoService commentDaoService, PostDaoService postDaoService) {
        this.commentDaoService = commentDaoService;
        this.postDaoService = postDaoService;
    }

    public List<CommentDto> getAll() {
        final List<Comment> commentList = commentDaoService.getAll();
        return CommentMapper.toDto(commentList);
    }

    public CommentDto getOneById(int id) {
        final Optional<Comment> optionalComment = commentDaoService.getOneById(id);
        if (optionalComment.isEmpty()) {
            throw new RuntimeException("Comment Not Found");
        }
        return CommentMapper.toDto(optionalComment.get());
    }

    public CommentDto create(CommentDto commentDto) {
        if (!postDaoService.isExitsById(commentDto.getPostId())) {
            throw new RuntimeException("Post Not Found");
        }

        final Optional<Post> optionalPost = postDaoService.getOneById(commentDto.getPostId());
        final Post post = optionalPost.get();

        final Comment comment = CommentMapper.toEntity(commentDto);
        comment.setPost(post);
        post.getComments().add(comment);

        final Comment savedComment = commentDaoService.create(comment);
        postDaoService.update(post);

        return CommentMapper.toDto(savedComment);
    }

    public CommentDto update(CommentDto commentDto) {
        if (!postDaoService.isExitsById(commentDto.getPostId())) {
            throw new RuntimeException("Post Not Found");
        }

        final Optional<Post> optionalPost = postDaoService.getOneById(commentDto.getPostId());
        final Post post = optionalPost.get();

        final Comment comment = CommentMapper.toEntity(commentDto);
        comment.setPost(post);

        final Comment updatedComment = commentDaoService.update(comment);

        return CommentMapper.toDto(updatedComment);
    }

    public boolean delete(int id) {
        return commentDaoService.delete(id);
    }

}



















