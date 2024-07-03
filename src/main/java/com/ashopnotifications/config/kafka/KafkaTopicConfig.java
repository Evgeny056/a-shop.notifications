package com.ashopnotifications.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    private String sentOrdersTopic = "sent_orders";

    @Bean
    public NewTopic sentOrdersTopic() {
        return TopicBuilder
                .name(sentOrdersTopic)
                .build();
    }
}
