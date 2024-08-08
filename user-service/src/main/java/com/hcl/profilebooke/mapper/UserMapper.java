package com.hcl.profilebooke.mapper;

import com.hcl.profilebooke.dto.MessageDto;
import com.hcl.profilebooke.dto.PostDto;
import com.hcl.profilebooke.dto.UserProfileDto;
import com.hcl.profilebooke.model.Message;
import com.hcl.profilebooke.model.Post;
import com.hcl.profilebooke.model.UserProfile;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {


    public static List<UserProfileDto> toDto(List<UserProfile> userList) {
        return  userList.stream().map(user -> toDto(user))
                .collect(Collectors.toList());
    }

    public static UserProfileDto toDto(UserProfile user) {
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setId(user.getId());
        userProfileDto.setEmail(user.getEmail());
        userProfileDto.setUsername(user.getUsername());
        userProfileDto.setPassword(user.getPassword());
        userProfileDto.setUserType(user.getUserType());
        userProfileDto.setPostDtos(PostMapper.toDto(user.getPosts()));
        userProfileDto.setSendMessageDtos(MessageMapper.toDto(user.getSendMessages()));
        userProfileDto.setReceivedMessageDtos(MessageMapper.toDto(user.getReceivedMessages()));


        return userProfileDto;
    }

    public static UserProfile toEntity(UserProfileDto userProfileDto) {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(userProfileDto.getId());
        userProfile.setUsername(userProfileDto.getUsername());
        userProfile.setPassword(userProfileDto.getPassword());
        userProfile.setEmail(userProfileDto.getEmail());
        userProfile.setUserType(userProfileDto.getUserType());

        final List<PostDto> postDtos = userProfileDto.getPostDtos();
        final List<Post> postList = PostMapper.toEntity(postDtos);

        userProfile.setPosts(postList);
        postList.forEach(post -> post.setUser(userProfile));

        final List<MessageDto> sendMessageDtos = userProfileDto.getSendMessageDtos();
        final List<Message> messageList = MessageMapper.toEntity(sendMessageDtos);

        userProfile.setSendMessages(messageList);
        messageList.forEach(message -> message.setSenderId(userProfile));

        final List<MessageDto> receivedMessageDtos = userProfileDto.getReceivedMessageDtos();
        final List<Message> receivedMessageList = MessageMapper.toEntityReceivedMessages(receivedMessageDtos);

        userProfile.setReceivedMessages(receivedMessageList);
        receivedMessageList.forEach(message -> message.setReceiverId(userProfile));

        return userProfile;
    }

}

























