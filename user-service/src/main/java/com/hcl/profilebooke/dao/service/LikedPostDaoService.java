package com.hcl.profilebooke.dao.service;

import com.hcl.profilebooke.dto.LikedPostDto;
import com.hcl.profilebooke.model.Comment;
import com.hcl.profilebooke.model.LikedPost;
import com.hcl.profilebooke.model.Post;
import com.hcl.profilebooke.repository.LikedPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikedPostDaoService {

    private final LikedPostRepository likedPostRepository;

    public LikedPostDaoService(LikedPostRepository likedPostRepository) {
        this.likedPostRepository = likedPostRepository;
    }

    public List<LikedPost> getAll() {
        return likedPostRepository.findAll();
    }

    public Optional<LikedPost> getOneById(int id) {
        return likedPostRepository.findById(id);
    }

    public LikedPost create(LikedPost likedPost) {
        return likedPostRepository.save(likedPost);
    }

    public LikedPost update(LikedPost likedPost) {
        return likedPostRepository.save(likedPost);
    }

    public boolean delete(int id) {


        likedPostRepository.deleteById(id);
        return true;
    }
}
