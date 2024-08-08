package com.hcl.profilebooke.repository;


import com.hcl.profilebooke.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Integer> {
}
