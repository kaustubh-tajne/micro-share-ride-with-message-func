package com.hcl.ownerservice.controller;

import com.hcl.ownerservice.dto.OwnerDto;
import com.hcl.ownerservice.dto.PostDto;
import com.hcl.ownerservice.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profileBookService/v1/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    private ResponseEntity<List<OwnerDto>> getAll() {
        final List<OwnerDto> result = ownerService.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<OwnerDto> getOneById(@PathVariable int ownerId) {
        OwnerDto result = ownerService.getOneById(ownerId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<OwnerDto> create(@RequestBody OwnerDto ownerDto) {
        final OwnerDto result = ownerService.create(ownerDto);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<OwnerDto> update(@RequestBody OwnerDto ownerDto) {
        final OwnerDto result = ownerService.update(ownerDto);
        if (result == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{ownerId}")
    public ResponseEntity<Void> delete(@PathVariable int ownerId) {
        boolean isDeleted = ownerService.delete(ownerId);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> result = ownerService.getAllPosts();
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{postId}/approve")
    public ResponseEntity<?> approvePost(@PathVariable int postId) {
        PostDto result = ownerService.approvePost(postId);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{postId}/reject")
    public ResponseEntity<?> rejectPost(@PathVariable int postId) {
        PostDto result = ownerService.rejectPost(postId);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

}
