package com.hcl.profilebooke.service;

import com.hcl.profilebooke.dao.service.LikedPostDaoService;
import com.hcl.profilebooke.dao.service.PostDaoService;
import com.hcl.profilebooke.dao.service.UserDaoService;
import com.hcl.profilebooke.dto.LikedPostDto;
import com.hcl.profilebooke.mapper.LikedPostMapper;
import com.hcl.profilebooke.model.LikedPost;
import com.hcl.profilebooke.model.Post;
import com.hcl.profilebooke.model.UserProfile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikedPostService {

    private final LikedPostDaoService likedPostDaoService;

    private final UserDaoService userDaoService;

    private final PostDaoService postDaoService;

    public LikedPostService(LikedPostDaoService likedPostDaoService, UserDaoService userDaoService, PostDaoService postDaoService) {
        this.likedPostDaoService = likedPostDaoService;
        this.userDaoService = userDaoService;
        this.postDaoService = postDaoService;
    }

    public List<LikedPostDto> getAll() {
        List<LikedPost> likedPostList = likedPostDaoService.getAll();

        return LikedPostMapper.toDto(likedPostList);
    }

    public LikedPostDto getOneById(int id) {
        Optional<LikedPost> optionalLikedPost = likedPostDaoService.getOneById(id);
        if (optionalLikedPost.isEmpty()) {
            throw new RuntimeException("No LikedPost Found");
        }
        return LikedPostMapper.toDto(optionalLikedPost.get());
    }

    public LikedPostDto create(LikedPostDto likedPostDto) {
        if (!userDaoService.existById(likedPostDto.getUserProfileId())) {
            throw new RuntimeException("User Not Found");
        }

        if (!postDaoService.isExitsById(likedPostDto.getPostId())) {
            throw new RuntimeException("Post Not Found");
        }

        final Optional<UserProfile> optionalUserProfile = userDaoService.getOneById(likedPostDto.getUserProfileId());
        final UserProfile userProfile = optionalUserProfile.get();

        final Optional<Post> optionalPost = postDaoService.getOneById(likedPostDto.getPostId());
        final Post post = optionalPost.get();

        final LikedPost likedPost = LikedPostMapper.toEntity(likedPostDto);

        likedPost.setUserProfile(userProfile);
        likedPost.setPost(post);

        post.getLikedPostList().add(likedPost);

        final LikedPost savedLikedPost = likedPostDaoService.create(likedPost);
        postDaoService.update(post);

        return LikedPostMapper.toDto(savedLikedPost);
    }

    public boolean delete(int id) {
        return likedPostDaoService.delete(id);
    }
}


















