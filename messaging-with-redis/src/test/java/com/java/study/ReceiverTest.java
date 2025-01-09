package com.java.study;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ReceiverTest {

    @Nested
    class receiveMessage_메소드는 {

        @Test
        void 메시지를_받으면_카운터를_증가시킨다() {
            // given
            Receiver receiver = new Receiver();
            String message = "Hello World!";

            // when
            receiver.receiveMessage(message);

            // then
            assertEquals(1, receiver.getCount());
        }
    }

    @Nested
    class getCount_메소드는 {

        @Test
        void 카운터를_반환한다() {
            // given
            Receiver receiver = new Receiver();
            String message = "Hello World!";
            receiver.receiveMessage(message);

            // when
            int count = receiver.getCount();

            // then
            assertEquals(1, count);
        }
    }
}