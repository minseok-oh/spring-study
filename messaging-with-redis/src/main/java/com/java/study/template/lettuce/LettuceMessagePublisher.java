package com.java.study.template.lettuce;

import com.java.study.template.RedisMessagePublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Profile("lettuce")
public class LettuceMessagePublisher implements RedisMessagePublisher {

    private final StringRedisTemplate stringRedisTemplate;

    public LettuceMessagePublisher(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void publish(String channel, String message) {
        stringRedisTemplate.convertAndSend(channel, message);
    }
}
