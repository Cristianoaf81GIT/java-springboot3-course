package br.com.cristianoaf81.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends java.lang.RuntimeException {
    private static final long serialVersionUID = 3384769583687096054L;

    public UnsupportedMathOperationException(String message) {
        super(message);
    }
}
