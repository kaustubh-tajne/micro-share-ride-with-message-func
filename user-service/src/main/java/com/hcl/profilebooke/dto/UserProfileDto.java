package com.hcl.profilebooke.dto;

import com.hcl.profilebooke.enums.UserType;

import java.util.List;

public class UserProfileDto {

    private int id;

    private String username;

    private String password;

    private UserType userType;

    private String email;

    private List<PostDto> postDtos;

    private List<MessageDto> sendMessageDtos;

    private List<MessageDto> receivedMessageDtos;


    public UserProfileDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PostDto> getPostDtos() {
        return postDtos;
    }

    public void setPostDtos(List<PostDto> postDtos) {
        this.postDtos = postDtos;
    }

    public List<MessageDto> getSendMessageDtos() {
        return sendMessageDtos;
    }

    public void setSendMessageDtos(List<MessageDto> sendMessageDtos) {
        this.sendMessageDtos = sendMessageDtos;
    }

    public List<MessageDto> getReceivedMessageDtos() {
        return receivedMessageDtos;
    }

    public void setReceivedMessageDtos(List<MessageDto> receivedMessageDtos) {
        this.receivedMessageDtos = receivedMessageDtos;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", email='" + email + '\'' +
                ", postDtos=" + postDtos +
                ", sendMessageDtos=" + sendMessageDtos +
                ", receivedMessageDtos=" + receivedMessageDtos +
                '}';
    }
}
