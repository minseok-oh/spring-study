package com.java.study.api;

import com.java.study.quote.Quote;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientApiTemplate implements ApiTemplate {

    private final WebClient webClient;

    public WebClientApiTemplate() {
        this.webClient = WebClient.builder().build();
    }

    @Override
    public Quote getForQuote(String uri) {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Quote.class)
                .block();
    }
}
