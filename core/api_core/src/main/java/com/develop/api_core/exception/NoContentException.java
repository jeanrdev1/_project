package com.develop.api_core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoContentException extends ResponseStatusException {

    public NoContentException() {
        super(HttpStatus.NO_CONTENT, "No Content");
    }
}
