package com.java.study.api;

import com.java.study.quote.Quote;

public interface ApiTemplate {
    Quote getForQuote(String uri);
}
