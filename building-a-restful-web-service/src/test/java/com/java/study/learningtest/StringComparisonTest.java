package com.java.study.learningtest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class StringComparisonTest {

    interface Callback {
        void execute();
    }

    private void measureMethodTime(Callback callback) {
        long start = System.nanoTime();
        callback.execute();
        long end = System.nanoTime();

        System.out.println(end - start);
    }

    @Test
    public void compareStringPerformance() {
        measureMethodTime(() -> {
            String template = "Hello, ";
            for (int i = 0; i < 10000; i++) {
                template += "World";
            }
        });

        measureMethodTime(() -> {
            String template = "Hello, ";
            for (int i = 0; i < 10000; i++) {
                template.concat("World");
            }
        });

        measureMethodTime(() -> {
            StringBuffer template = new StringBuffer("Hello, ");
            for (int i = 0; i < 10000; i++) {
                template.append("World");
            }
        });

        measureMethodTime(() -> {
            StringBuilder template = new StringBuilder("Hello, ");
            for (int i = 0; i < 10000; i++) {
                template.append("World");
            }
        });
    }

    @Test
    public void compareStringConcurrency() throws InterruptedException {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder();

        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                stringBuffer.append(1);
                stringBuilder.append(1);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                stringBuffer.append(1);
                stringBuilder.append(1);
            }
        }).start();

        Thread.sleep(2000);
        assertThat(stringBuffer.length()).isEqualTo(20000);
        assertThat(stringBuilder.length()).isNotEqualTo(20000);
    }
}
