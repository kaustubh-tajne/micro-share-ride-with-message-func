package com.hcl.profilebooke.controller;

import com.hcl.profilebooke.dto.LikedPostDto;
import com.hcl.profilebooke.model.LikedPost;
import com.hcl.profilebooke.service.LikedPostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profileBookingService/v1/likedPosts")
public class LikedPostController {

    private final LikedPostService likedPostService;

    public LikedPostController(LikedPostService likedPostService) {
        this.likedPostService = likedPostService;
    }

    @GetMapping
    public ResponseEntity<List<LikedPostDto>> getAll() {
        final List<LikedPostDto> result = likedPostService.getAll();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{likedPostId}")
    public ResponseEntity<LikedPostDto> getOneById(@PathVariable int likedPostId) {
        final LikedPostDto result = likedPostService.getOneById(likedPostId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<LikedPostDto> create(@Valid @RequestBody LikedPostDto likedPostDto) {
        final LikedPostDto result = likedPostService.create(likedPostDto);
        if (result == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{likedPostId}")
    public ResponseEntity<Void> delete(@PathVariable int likedPostId) {
        final boolean isDeleted = likedPostService.delete(likedPostId);
        if (!isDeleted) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }
}














