package com.hcl.profilebooke.dao.service;

import com.hcl.profilebooke.model.Comment;
import com.hcl.profilebooke.model.Post;
import com.hcl.profilebooke.repository.CommentRepository;
import com.hcl.profilebooke.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentDaoService {

    private CommentRepository commentRepository;

    private final PostRepository postRepository;

    public CommentDaoService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getOneById(int id) {
        return commentRepository.findById(id);
    }

    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment update(Comment comment) {
        return commentRepository.save(comment);
    }

    public boolean delete(int id) {
        final Optional<Comment> optionalComment = commentRepository.findById(id);

        if (optionalComment.isEmpty()) {
            throw new RuntimeException("Post Not Found");
        }

        final Comment comment = optionalComment.get();

        final Post post = comment.getPost();
        post.getComments().remove(comment);

        commentRepository.deleteById(id);
        return true;
    }

}
