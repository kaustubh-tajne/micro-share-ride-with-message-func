package com.hcl.profilebooke.dto;

import com.hcl.profilebooke.enums.StatusType;

import java.util.List;

public class PostDto {

    private int id;

    private String content;

    private StatusType statusType;

    private int userId;

    private List<CommentDto> commentDtos;

    private List<LikedPostDto> likedPostDtos;

    public PostDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<CommentDto> getCommentDtos() {
        return commentDtos;
    }

    public void setCommentDtos(List<CommentDto> commentDtos) {
        this.commentDtos = commentDtos;
    }

    public List<LikedPostDto> getLikedPostDtos() {
        return likedPostDtos;
    }

    public void setLikedPostDtos(List<LikedPostDto> likedPostDtos) {
        this.likedPostDtos = likedPostDtos;
    }
}
