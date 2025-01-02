package com.java.study.global.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class S3ConfigTest {

    @Value("${aws.s3.accessKey}")
    private String accessKey;

    @Value("${aws.s3.secretKey}")
    private String secretKey;

    @Test
    void 동일한_액세스_키를_가져온다() {
        assertThat(accessKey).isEqualTo("aws-s3-access-key");
    }

    @Test
    void 동일한_시크릿_키를_가져온다() {
        assertThat(secretKey).isEqualTo("aws-s3-secret-key");
    }
}