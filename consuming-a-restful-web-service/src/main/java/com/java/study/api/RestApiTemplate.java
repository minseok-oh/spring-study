package com.java.study.api;

import com.java.study.quote.Quote;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class RestApiTemplate implements ApiTemplate{

    private final RestTemplate template;

    public RestApiTemplate() {
        this.template = new RestTemplate(new JdkClientHttpRequestFactory());
    }

    @Override
    public Quote getForQuote(String uri) {
        return template.getForObject(uri, Quote.class);
    }
}
