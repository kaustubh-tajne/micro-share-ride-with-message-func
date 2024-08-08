package com.hcl.profilebooke.dao.service;


import com.hcl.profilebooke.model.UserProfile;
import com.hcl.profilebooke.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDaoService {

    private UserRepository userRepository;

    public UserDaoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserProfile> getAll() {
        return userRepository.findAll();
    }
    public boolean existById(int id) {
        final Optional<UserProfile> optionalUserProfile = userRepository.findById(id);
        return optionalUserProfile.isPresent();
    }

    public Optional<UserProfile> getOneById(int id) {
        return userRepository.findById(id);
    }

    public UserProfile create(UserProfile user) {
        return userRepository.save(user);
    }

    public UserProfile update(UserProfile user) {
        return userRepository.save(user);
    }

    public boolean delete(int id) {
        userRepository.deleteById(id);
        return true;
    }
}
