package com.hcl.profilebooke.model;

import jakarta.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id", nullable = false)
    private int id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id", nullable = false)
    private UserProfile senderId;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "user_id", nullable = false)
    private UserProfile receiverId;

    public Message() {
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

    public UserProfile getSenderId() {
        return senderId;
    }

    public void setSenderId(UserProfile senderId) {
        this.senderId = senderId;
    }

    public UserProfile getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(UserProfile receiverId) {
        this.receiverId = receiverId;
    }

    //    @Override
//    public String toString() {
//        return "Message{" +
//                "id=" + id +
//                ", content='" + content + '\'' +
//                ", senderId=" + senderId +
//                ", receiverId=" + receiverId +
//                '}';
//    }
}
