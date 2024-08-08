package com.hcl.profilebooke.mapper;

import com.hcl.profilebooke.dto.MessageDto;
import com.hcl.profilebooke.model.Message;
import com.hcl.profilebooke.model.UserProfile;
import com.hcl.profilebooke.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageMapper {

    @Autowired
    private static UserRepository userRepository;

    public MessageMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static List<MessageDto> toDto(List<Message> messageList) {
        return messageList.stream()
                .map(message -> toDto(message))
                .collect(Collectors.toList());
    }

    public static MessageDto toDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setContent(message.getContent());
        messageDto.setSenderId(message.getSenderId().getId());
        messageDto.setReceiverId(message.getReceiverId().getId());
        return messageDto;
    }


    public static List<Message> toEntity(List<MessageDto> messageDtos) {
        return messageDtos.stream()
                .map(messageDto -> toEntity(messageDto))
                .collect(Collectors.toList());
    }

    public static Message toEntity(MessageDto messageDto) {
        Message message = new Message();
        message.setId(messageDto.getId());
        message.setContent(messageDto.getContent());
//        message.setSenderId();
        final UserProfile receiverUser = userRepository.findById(messageDto.getReceiverId()).orElseThrow(() -> new IllegalArgumentException("Receiver User not found"));
        message.setReceiverId(receiverUser);

        return message;
    }

    public static List<Message> toEntityReceivedMessages(List<MessageDto> receivedMessageDtos) {
        return receivedMessageDtos.stream()
                .map(messageDto -> toEntityReceivedMessages(messageDto))
                .collect(Collectors.toList());
    }

    public static Message toEntityReceivedMessages(MessageDto messageDto) {
        Message message = new Message();
        message.setId(messageDto.getId());
        message.setContent(messageDto.getContent());
        final UserProfile senderUser = userRepository.findById(messageDto.getSenderId()).orElseThrow(() -> new IllegalArgumentException("Sender User Not Found"));
        message.setSenderId(senderUser);
        return message;
    }
}
