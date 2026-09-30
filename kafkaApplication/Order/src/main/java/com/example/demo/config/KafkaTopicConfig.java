package com.example.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    //creates an instance of a new topic
    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(topicName)
        		//.partitions(0) if partition is required
                .build();
    }
}
