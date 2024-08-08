package com.hcl.profilebooke.repository;

import com.hcl.profilebooke.model.LikedPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedPostRepository extends JpaRepository<LikedPost, Integer> {
}
