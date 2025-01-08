package com.java.study.template.redisson;

import com.java.study.template.RedisMessagePublisher;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("redisson")
public class RedissonMessagePublisher implements RedisMessagePublisher {

    private final RTopic topic;

    public RedissonMessagePublisher(RedissonClient redissonClient) {
        this.topic = redissonClient.getTopic("chat");
    }

    @Override
    public void publish(String channel, String message) {
        topic.publish(message);
    }
}
