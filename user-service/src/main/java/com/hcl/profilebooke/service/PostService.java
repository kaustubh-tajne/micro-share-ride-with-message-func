package com.hcl.profilebooke.service;

import com.hcl.profilebooke.dao.service.PostDaoService;
import com.hcl.profilebooke.dao.service.UserDaoService;
import com.hcl.profilebooke.dto.PostDto;
import com.hcl.profilebooke.mapper.PostMapper;
import com.hcl.profilebooke.model.Post;
import com.hcl.profilebooke.model.UserProfile;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostDaoService postDaoService;

    private final UserService userService;

    private final UserDaoService userDaoService;

    public PostService(PostDaoService postDaoService, UserService userService, UserDaoService userDaoService) {
        this.postDaoService = postDaoService;
        this.userService = userService;
        this.userDaoService = userDaoService;
    }

    public List<PostDto> getAll() {
        final List<Post> postList = postDaoService.getAll();
        return PostMapper.toDto(postList);
    }

    public PostDto getOneById(int id) {
        final Optional<Post> optionalPost = postDaoService.getOneById(id);
        if (optionalPost.isEmpty()) {
            throw new RuntimeException("Post Not Found");
        }
        return PostMapper.toDto(optionalPost.get());
    }

    public PostDto create(PostDto postDto) {
        if (!userService.existById(postDto.getUserId())) {
            throw new RuntimeException("User not found");
        }

        final Optional<UserProfile> optionalUserProfile = userDaoService.getOneById(postDto.getUserId());
        final UserProfile userProfile = optionalUserProfile.get();

        final Post post = PostMapper.toEntity(postDto);

        post.setUser(userProfile);
        userProfile.getPosts().add(post);

        final Post savedPost = postDaoService.create(post);
        userDaoService.update(userProfile);

        return PostMapper.toDto(savedPost);
    }

    public PostDto update(PostDto postDto) {
        if (!userService.existById(postDto.getUserId())) {
            throw new RuntimeException("User not found");
        }

        final Optional<UserProfile> optionalUserProfile = userDaoService.getOneById(postDto.getUserId());
        final UserProfile userProfile = optionalUserProfile.get();

        final Post post = PostMapper.toEntity(postDto);
        post.setUser(userProfile);

        final Post updatedPost = postDaoService.update(post);
        return PostMapper.toDto(updatedPost);
    }

    public boolean delete(int id) {
        return postDaoService.delete(id);
    }
}

















