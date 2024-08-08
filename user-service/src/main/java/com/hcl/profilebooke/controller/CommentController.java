package com.hcl.profilebooke.controller;

import com.hcl.profilebooke.dto.CommentDto;
import com.hcl.profilebooke.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profileBookingService/v1/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAll() {
        final List<CommentDto> result = commentService.getAll();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getOneById(@PathVariable int commentId) {
        final CommentDto result = commentService.getOneById(commentId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<CommentDto> create(@Valid @RequestBody CommentDto commentDto) {
        final CommentDto result = commentService.create(commentDto);
        if (result == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CommentDto> update(@RequestBody CommentDto commentDto) {

        final CommentDto result = commentService.update(commentDto);
        if (result == null) {
            return ResponseEntity.unprocessableEntity()
                    .build();
        }
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable int commentId) {
        final boolean isDeleted = commentService.delete(commentId);
        if (!isDeleted) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }
}
















