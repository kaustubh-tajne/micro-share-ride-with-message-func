package com.hcl.profilebooke.dao.service;

import com.hcl.profilebooke.model.Post;
import com.hcl.profilebooke.model.UserProfile;
import com.hcl.profilebooke.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostDaoService {

    private PostRepository postRepository;

    public PostDaoService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Optional<Post> getOneById(int id) {
        return postRepository.findById(id);
    }

    public boolean isExitsById(int id) {
        return postRepository.findById(id).isPresent();
    }

    public Post create(Post post) {
        return postRepository.save(post);
    }

    public Post update(Post post) {
        return postRepository.save(post);
    }

    public boolean delete(int id) {
        final Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            throw new RuntimeException("Post not found");
        }

        final Post post = optionalPost.get();
        final UserProfile userProfile = post.getUser();

        userProfile.getPosts().remove(post);

        postRepository.deleteById(id);
        return true;
    }
}
