package com.tabisketch.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tabisketch.exception.NullResponseException;

public interface IGeminiRepository {
    String request(final String text) throws JsonProcessingException, NullResponseException;
}
