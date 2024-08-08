package com.hcl.profilebooke.mapper;

import com.hcl.profilebooke.dto.PostDto;
import com.hcl.profilebooke.model.Comment;
import com.hcl.profilebooke.model.LikedPost;
import com.hcl.profilebooke.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {

    public static List<PostDto> toDto(List<Post> postList) {
        return postList.stream()
                .map(post -> toDto(post))
                .collect(Collectors.toList());
    }

    public static PostDto toDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setContent(post.getContent());
        postDto.setStatusType(post.getStatusType());
        postDto.setUserId(post.getUser().getId());
        postDto.setCommentDtos(CommentMapper.toDto(post.getComments()));
        postDto.setLikedPostDtos(LikedPostMapper.toDto(post.getLikedPostList()));
        return postDto;
    }

    public static List<Post> toEntity(List<PostDto> postDtoList) {
        return postDtoList.stream()
                .map(postDto -> toEntity(postDto))
                .collect(Collectors.toList());
    }

    public static Post toEntity(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setContent(postDto.getContent());
        post.setStatusType(postDto.getStatusType());
//        post.setUser(postDto.getUserId());
        final List<Comment> commentList = CommentMapper.toEntity(postDto.getCommentDtos());

        post.setComments(commentList);
        commentList.forEach(comment -> comment.setPost(post));

        final List<LikedPost> likedPostList = LikedPostMapper.toEntity(postDto.getLikedPostDtos());

        post.setLikedPostList(likedPostList);
        likedPostList.forEach(likedPost -> likedPost.setPost(post));

        return post;
    }
}













