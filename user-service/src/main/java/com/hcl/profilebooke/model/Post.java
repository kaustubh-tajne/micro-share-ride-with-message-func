package com.hcl.profilebooke.model;

import com.hcl.profilebooke.enums.StatusType;
import jakarta.persistence.*;
import jdk.jshell.Snippet;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id", nullable = false)
    private int id;

    private String content;

    private StatusType statusType;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfile user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<LikedPost> likedPostList = new ArrayList<>();

    public Post() {
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

    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<LikedPost> getLikedPostList() {
        return likedPostList;
    }

    public void setLikedPostList(List<LikedPost> likedPostList) {
        this.likedPostList = likedPostList;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", statusType=" + statusType +
                ", user=" + user +
                '}';
    }
}
