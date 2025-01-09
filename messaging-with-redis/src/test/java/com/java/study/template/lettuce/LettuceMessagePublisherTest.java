package com.java.study.template.lettuce;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.StringRedisTemplate;

class LettuceMessagePublisherTest {

    @Nested
    class publish_메소드는 {

        @Test
        void convertAndSend를_호출한다() {
            StringRedisTemplate stringRedisTemplate = mock(StringRedisTemplate.class);
            LettuceMessagePublisher lettuceMessagePublisher = new LettuceMessagePublisher(stringRedisTemplate);
            String channel = "chat";
            String message = "hello";

            lettuceMessagePublisher.publish(channel, message);

            verify(stringRedisTemplate).convertAndSend(channel, message);
        }
    }
}