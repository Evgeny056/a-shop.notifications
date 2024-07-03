package com.ashopnotifications.messaging.listener;

import com.ashopnotifications.model.dto.CreateOrderRequestDto;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.MessagingException;

public interface MessageListener {
    void listenMessage(CreateOrderRequestDto createOrderRequestDto, Acknowledgment acknowledgment) throws MessagingException;
}
