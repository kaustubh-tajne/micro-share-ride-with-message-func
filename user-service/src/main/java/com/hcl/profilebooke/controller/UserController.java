package com.hcl.profilebooke.controller;

import com.hcl.profilebooke.dto.PostDto;
import com.hcl.profilebooke.dto.UserProfileDto;
import com.hcl.profilebooke.model.UserProfile;
import com.hcl.profilebooke.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.UserSessionEvent;
import java.util.List;

@RestController
@RequestMapping("/api/profileBookService/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping
    public ResponseEntity<List<UserProfileDto>> getAll() {
        final List<UserProfileDto> result = userService.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileDto> getOneById(@PathVariable int userId) {
        final UserProfileDto result = userService.getOneById(userId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<UserProfileDto> create(@RequestBody UserProfileDto userProfileDto) {
        final UserProfileDto result = userService.create(userProfileDto);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<UserProfileDto> update(@RequestBody UserProfileDto userProfileDto) {
        final UserProfileDto result = userService.update(userProfileDto);
        if (result == null) {
            return ResponseEntity.unprocessableEntity()
                    .build();
        }
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable int userId) {
        final boolean isDeleted = userService.delete(userId);
        if (!isDeleted) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{postId}/approve")
    public ResponseEntity<?> approvePost(@PathVariable int postId) {
        PostDto result = userService.approvePost(postId);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{postId}/reject")
    public ResponseEntity<?> rejectPost(@PathVariable int postId) {
        PostDto result = userService.rejectPost(postId);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }
}
