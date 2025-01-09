package com.java.study.template.redisson;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;

class RedissonMessagePublisherTest {

    @Nested
    class publish_메소드는 {

        @Test
        void publish를_호출한다() {
            RedissonClient redissonClient = mock(RedissonClient.class);
            when(redissonClient.getTopic("chat")).thenReturn(mock(RTopic.class));

            RedissonMessagePublisher redissonMessagePublisher = new RedissonMessagePublisher(redissonClient);
            String channel = "chat";
            String message = "hello";

            RTopic topic = redissonClient.getTopic(channel);
            redissonMessagePublisher.publish(channel, message);

            verify(topic).publish(message);
        }
    }
}