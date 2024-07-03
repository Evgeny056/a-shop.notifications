package com.ashopnotifications.service;

import com.ashopnotifications.exception.OrderNotFoundException;
import com.ashopnotifications.exception.UserNotFoundException;
import com.ashopnotifications.model.dto.CreateOrderRequestDto;
import com.ashopnotifications.model.entity.Order;
import com.ashopnotifications.model.entity.User;
import com.ashopnotifications.repository.OrderRepository;
import com.ashopnotifications.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final JavaMailSender javaMailSender;

    @Value("${sender}")
    private String sender;

    @Transactional
    public void sendEmail(CreateOrderRequestDto createOrderRequestDto) {
        if (createOrderRequestDto.getStatus().equalsIgnoreCase("DELIVERED")) {
            Order order = orderRepository.findById(createOrderRequestDto.getOrderId())
                    .orElseThrow(()-> new OrderNotFoundException("Order not found by id: " + createOrderRequestDto.getOrderId()));

            User user = userRepository.findById(order.getUser().getUserId())
                    .orElseThrow(()-> new UserNotFoundException("User not found by id" + createOrderRequestDto.getUserId()));

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(user.getEmail());
            message.setSubject("Заказ был успешно доставлен!");
            message.setText("Заказ был успешно доставлен! Номер заказа: " + createOrderRequestDto.getOrderId());
            javaMailSender.send(message);
            log.info("Пользователь: {} {} информирован об успешной доставке товара. Адрес получателя: {}", user.getLastName(), user.getFirstName(), user.getEmail());
        }

    }
}
