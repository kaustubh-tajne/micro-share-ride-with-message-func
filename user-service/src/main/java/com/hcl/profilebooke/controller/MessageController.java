package com.hcl.profilebooke.controller;

import com.hcl.profilebooke.dto.MessageDto;
import com.hcl.profilebooke.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profileService/v1/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<List<MessageDto>> getAll() {
        final List<MessageDto> result = messageService.getAll();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<MessageDto> getOneById(@PathVariable int messageId) {
        final MessageDto result = messageService.getOneById(messageId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<MessageDto> create(@Valid @RequestBody MessageDto messageDto) {
        final MessageDto result = messageService.create(messageDto);
        if (result == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MessageDto> update(@RequestBody MessageDto messageDto) {
        final MessageDto result = messageService.update(messageDto);
        if (result == null) {
            return ResponseEntity.unprocessableEntity()
                    .build();
        }
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> delete(@PathVariable int messageId) {
        final boolean isDeleted = messageService.delete(messageId);
        if (!isDeleted) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }
}
