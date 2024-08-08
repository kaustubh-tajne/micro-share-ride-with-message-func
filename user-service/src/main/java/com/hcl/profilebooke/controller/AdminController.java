package com.hcl.profilebooke.controller;

import com.hcl.profilebooke.dto.PostDto;
import com.hcl.profilebooke.service.PostService;
import com.hcl.profilebooke.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profileBookingService/v1/admin")
public class AdminController {

    private final UserService userService;

    private final PostService postService;

    public AdminController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAll() {
        final List<PostDto> posts = postService.getAll();
        return ResponseEntity.ok(posts);
    }
    @PostMapping("/posts/{postId}/approve")
    public ResponseEntity<PostDto> approvePost(@PathVariable int postId) {
        final PostDto result = userService.approvePost(postId);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

}
