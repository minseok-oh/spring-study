package com.java.study.storage;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class StoragePropertiesTest {

    private final StorageProperties storageProperties;

    StoragePropertiesTest() {
        this.storageProperties = new StorageProperties();
    }

    @Nested
    class getLocation_메서드는 {

        @Test
        void 기본_Location을_반환한다() {
            String location = storageProperties.getLocation();

            assertThat(location).isEqualTo("upload-dir");
        }
    }

    @Nested
    class setLocation_메서드는 {

        @Test
        void 새로운_Location을_등록한다() {
            String newLocation = "new-location";
            storageProperties.setLocation(newLocation);

            String savedLocation = storageProperties.getLocation();
            assertThat(savedLocation).isEqualTo(newLocation);
        }
    }
}