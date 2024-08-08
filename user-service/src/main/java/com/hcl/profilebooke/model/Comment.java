package com.hcl.profilebooke.model;

import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id", nullable = false)
    private int id;

    @Column(name = "content")
    private String content;

    @ManyToOne()
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment() {
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }



    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", Content='" + content + '\'' +
                ", post=" + post +
                '}';
    }
}
