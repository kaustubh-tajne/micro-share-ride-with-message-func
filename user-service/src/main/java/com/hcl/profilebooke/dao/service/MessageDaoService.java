package com.hcl.profilebooke.dao.service;

import com.hcl.profilebooke.model.Message;
import com.hcl.profilebooke.model.UserProfile;
import com.hcl.profilebooke.repository.MessageRepository;
import com.hcl.profilebooke.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageDaoService {

    private MessageRepository messageRepository;

    public MessageDaoService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    public Optional<Message> getOneById(int id) {
        return messageRepository.findById(id);
    }

    public Message create(Message message) {
        return messageRepository.save(message);
    }

    public Message update(Message message) {
        return messageRepository.save(message);
    }

    public boolean delete(int id) {
        final Optional<Message> optionalMessage = messageRepository.findById(id);

        if (optionalMessage.isEmpty()) {
            throw new RuntimeException("Message not found");
        }

        final Message message = optionalMessage.get();

        final UserProfile sender = message.getSenderId();
        final UserProfile receiver = message.getReceiverId();

        sender.getSendMessages().remove(message);
        receiver.getReceivedMessages().remove(message);

        messageRepository.deleteById(id);
        return true;
    }

}
