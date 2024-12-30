package com.java.study;

import com.java.study.api.ApiTemplate;
import com.java.study.api.RestApiTemplate;
import com.java.study.condition.NotTestProfileCondition;
import com.java.study.quote.Quote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ConsumingRestConfig {

    private final String QUOTE_SERVER_URI = "http://localhost:8080/api/random";

    @Bean
    public ApiTemplate apiTemplate() {
        return new RestApiTemplate();
    }

    @Bean
    @Conditional({NotTestProfileCondition.class})
    public CommandLineRunner run(ApiTemplate apiTemplate) {
        return args -> {
            Quote quote = apiTemplate.getForQuote(QUOTE_SERVER_URI);
            log.info(quote.toString());
        };
    }
}
