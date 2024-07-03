package com.ashopnotifications.model.dto;

import com.ashopnotifications.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequestDto {
    private long orderId;
    private long userId;
    private String status;
}
