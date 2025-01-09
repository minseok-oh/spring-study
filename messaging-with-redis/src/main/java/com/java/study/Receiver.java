package com.java.study;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Receiver {
    private AtomicInteger counter = new AtomicInteger();

    public void receiveMessage(String message) {
        log.info("Received <{}>", message);
        counter.incrementAndGet();
    }

    public int getCount() {
        return counter.get();
    }
}
