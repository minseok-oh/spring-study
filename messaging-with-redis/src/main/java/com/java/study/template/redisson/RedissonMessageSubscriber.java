package com.java.study.template.redisson;

import com.java.study.Receiver;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("redisson")
public class RedissonMessageSubscriber {
    private final RTopic topic;

    @Autowired
    public RedissonMessageSubscriber(RedissonClient redissonClient, Receiver receiver) {
        this.topic = redissonClient.getTopic("chat");
        topic.addListener(String.class, (channel, message) -> receiver.receiveMessage(message));
    }
}
