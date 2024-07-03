package com.ashopnotifications.messaging.listener.impl;

import com.ashopnotifications.messaging.listener.MessageListener;
import com.ashopnotifications.model.dto.CreateOrderRequestDto;
import com.ashopnotifications.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageListenerImpl implements MessageListener {

    private final String sentOrdersTopic = "sent_orders";
    private final String groupId = "notifications-group";
    private final MailService mailService;


    @Override
    @KafkaListener(topics = sentOrdersTopic, groupId = groupId)
    public void listenMessage(CreateOrderRequestDto createOrderRequestDto, Acknowledgment acknowledgment) throws MessagingException {
        log.info("Received message from shipping service: {}", createOrderRequestDto);
            mailService.sendEmail(createOrderRequestDto);
            acknowledgment.acknowledge();
    }
}
