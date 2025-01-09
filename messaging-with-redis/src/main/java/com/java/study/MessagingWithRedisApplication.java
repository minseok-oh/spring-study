package com.java.study;

import com.java.study.template.RedisMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class MessagingWithRedisApplication {

    @Bean
    Receiver receiver() {
        return new Receiver();
    }

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = SpringApplication.run(MessagingWithRedisApplication.class, args);

        RedisMessagePublisher template = ctx.getBean(RedisMessagePublisher.class);
        Receiver receiver = ctx.getBean(Receiver.class);

        while (receiver.getCount() == 0) {
            log.info("Sending message...");
            template.publish("chat", "Hello from Redis!");
            Thread.sleep(500L);
        }

        System.exit(0);
    }

}
