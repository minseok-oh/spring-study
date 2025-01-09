package com.java.study.template;

public interface RedisMessagePublisher {

    void publish(String channel, String message);
}
