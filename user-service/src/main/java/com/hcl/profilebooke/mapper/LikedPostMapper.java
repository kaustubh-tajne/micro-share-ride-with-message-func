package com.hcl.profilebooke.mapper;

import com.hcl.profilebooke.dto.LikedPostDto;
import com.hcl.profilebooke.dto.PostDto;
import com.hcl.profilebooke.model.LikedPost;
import com.hcl.profilebooke.model.UserProfile;
import com.hcl.profilebooke.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LikedPostMapper {

    @Autowired
    private static UserRepository userRepository;

    public LikedPostMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static List<LikedPostDto> toDto(List<LikedPost> likedPostList) {
        return likedPostList.stream()
                .map(likedPost -> toDto(likedPost))
                .collect(Collectors.toList());
    }

    public static LikedPostDto toDto(LikedPost likedPost) {
        LikedPostDto likedPostDto = new LikedPostDto();
        likedPostDto.setId(likedPost.getId());
        likedPostDto.setPostId(likedPost.getPost().getId());
        likedPostDto.setUserProfileId(likedPost.getUserProfile().getId());
        return likedPostDto;
    }


    public static List<LikedPost> toEntity(List<LikedPostDto> likedPostDtoList) {
        return likedPostDtoList.stream()
                .map(likedPostDto -> toEntity(likedPostDto))
                .collect(Collectors.toList());
    }

    public static LikedPost toEntity(LikedPostDto likedPostDto) {
        LikedPost likedPost = new LikedPost();
        likedPost.setId(likedPostDto.getId());

        final UserProfile userProfile = userRepository.findById(likedPostDto.getUserProfileId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        likedPost.setUserProfile(userProfile);

        return likedPost;
    }
}
