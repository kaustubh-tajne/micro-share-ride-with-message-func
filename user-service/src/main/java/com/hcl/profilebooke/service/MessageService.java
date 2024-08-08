package com.hcl.profilebooke.service;

import com.hcl.profilebooke.dao.service.MessageDaoService;
import com.hcl.profilebooke.dao.service.UserDaoService;
import com.hcl.profilebooke.dto.MessageDto;
import com.hcl.profilebooke.mapper.MessageMapper;
import com.hcl.profilebooke.model.Message;
import com.hcl.profilebooke.model.UserProfile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageDaoService messageDaoService;

    private final UserDaoService userDaoService;

    public MessageService(MessageDaoService messageDaoService, UserDaoService userDaoService) {
        this.messageDaoService = messageDaoService;
        this.userDaoService = userDaoService;
    }

    public List<MessageDto> getAll() {
        List<Message> accountList = messageDaoService.getAll();

        return MessageMapper.toDto(accountList);
    }

    public MessageDto getOneById(int id) {
        Optional<Message> optionalMessage = messageDaoService.getOneById(id);
        if (optionalMessage.isEmpty()) {
            throw new RuntimeException("No message found");
        }
        return MessageMapper.toDto(optionalMessage.get());
    }

    public MessageDto create(MessageDto messageDto) {
        if (!userDaoService.existById(messageDto.getSenderId())) {
            throw new RuntimeException("Sender user not found");
        }

        if (!userDaoService.existById(messageDto.getReceiverId())) {
            throw new RuntimeException("Receiver user not found");
        }

        final Optional<UserProfile> optionalSender = userDaoService.getOneById(messageDto.getSenderId());
        final UserProfile sender = optionalSender.get();

        final Optional<UserProfile> optionalReceiver = userDaoService.getOneById(messageDto.getReceiverId());
        final UserProfile receiver = optionalReceiver.get();

        Message message = new Message();
        message.setId(messageDto.getId());
        message.setContent(messageDto.getContent());
        message.setSenderId(sender);
        message.setReceiverId(receiver);

        sender.getSendMessages().add(message);

        receiver.getReceivedMessages().add(message);

        final Message savedMessage = messageDaoService.create(message);

        userDaoService.update(sender);
        userDaoService.update(receiver);

        return MessageMapper.toDto(savedMessage);
    }

    public MessageDto update(MessageDto messageDto) {
        if (!userDaoService.existById(messageDto.getSenderId())) {
            throw new RuntimeException("Sender user not found");
        }

        if (!userDaoService.existById(messageDto.getReceiverId())) {
            throw new RuntimeException("Receiver user not found");
        }

        final Optional<UserProfile> optionalSender = userDaoService.getOneById(messageDto.getSenderId());
        final UserProfile sender = optionalSender.get();

        final Optional<UserProfile> optionalReceiver = userDaoService.getOneById(messageDto.getReceiverId());
        final UserProfile receiver = optionalReceiver.get();

        Message message = new Message();
        message.setId(messageDto.getId());
        message.setContent(messageDto.getContent());
        message.setSenderId(sender);
        message.setReceiverId(receiver);

        final Message savedMessage = messageDaoService.create(message);

        return MessageMapper.toDto(savedMessage);
    }

    public boolean delete(int id) {
        return messageDaoService.delete(id);
    }
}















