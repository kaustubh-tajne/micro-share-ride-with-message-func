package com.hcl.profilebooke.service;

import com.hcl.profilebooke.dao.service.PostDaoService;
import com.hcl.profilebooke.dao.service.UserDaoService;
import com.hcl.profilebooke.dto.PostDto;
import com.hcl.profilebooke.dto.UserProfileDto;
import com.hcl.profilebooke.enums.StatusType;
import com.hcl.profilebooke.mapper.PostMapper;
import com.hcl.profilebooke.mapper.UserMapper;
import com.hcl.profilebooke.model.Post;
import com.hcl.profilebooke.model.UserProfile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDaoService userDaoService;

    private final PostDaoService postDaoService;

    public UserService(UserDaoService userDaoService, PostDaoService postDaoService) {
        this.userDaoService = userDaoService;
        this.postDaoService = postDaoService;
    }

    public List<UserProfileDto> getAll() {
        List<UserProfile> userList = userDaoService.getAll();
        return UserMapper.toDto(userList);
    }

    public UserProfileDto getOneById(int id) {
        final Optional<UserProfile> optionalUserProfile = userDaoService.getOneById(id);
        if (optionalUserProfile.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return UserMapper.toDto(optionalUserProfile.get());
    }

    public boolean existById(int id) {
        final Optional<UserProfile> optionalUserProfile = userDaoService.getOneById(id);
        return optionalUserProfile.isPresent();
    }

    public UserProfileDto create(UserProfileDto userProfileDto) {
        final UserProfile userProfile = UserMapper.toEntity(userProfileDto);

        final UserProfile savedUserProfile = userDaoService.create(userProfile);
        return UserMapper.toDto(savedUserProfile);
    }

    public UserProfileDto update(UserProfileDto userProfileDto) {
        final UserProfile userProfile = UserMapper.toEntity(userProfileDto);
        final UserProfile updateUserProfile = userDaoService.update(userProfile);
        return UserMapper.toDto(updateUserProfile);
    }

    public boolean delete(int id) {
        return userDaoService.delete(id);
    }

    public PostDto approvePost(int postId) {
        if (!postDaoService.isExitsById(postId)) {
            throw new RuntimeException("Post Not Found");
        }

        final Optional<Post> optionalPost = postDaoService.getOneById(postId);
        final Post post = optionalPost.get();

        post.setStatusType(StatusType.APPROVED);
        final Post updatedPost = postDaoService.update(post);

        return PostMapper.toDto(updatedPost);
    }

    public PostDto rejectPost(int postId) {
        if (!postDaoService.isExitsById(postId)) {
            throw new RuntimeException("Post Not Found");
        }

        final Optional<Post> optionalPost = postDaoService.getOneById(postId);
        final Post post = optionalPost.get();

        post.setStatusType(StatusType.REJECT);
        final Post updatedPost = postDaoService.update(post);

        return PostMapper.toDto(updatedPost);
    }
}









