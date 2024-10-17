package br.com.cristianoaf81.exceptions.handler;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.cristianoaf81.exceptions.ExceptionResponse;
import br.com.cristianoaf81.exceptions.RequiredObjectsIsNullException;
import br.com.cristianoaf81.exceptions.ResourceNotFoundException;

// @ControllerAdvice
// @RestController
@RestControllerAdvice
class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        Date timestamp = new Date();
        String message = ex.getMessage();
        String details = request.getDescription(false);
        ExceptionResponse exceptionResponse = new ExceptionResponse(timestamp, message, details);
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex, WebRequest request) {
        Date timestamp = new Date();
        String message = ex.getMessage();
        String details = request.getDescription(false);
        ExceptionResponse exceptionResponse = new ExceptionResponse(timestamp, message, details);
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RequiredObjectsIsNullException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestException(Exception ex, WebRequest request) {
        Date timestamp = new Date();
        String message = ex.getMessage();
        String details = request.getDescription(false);
        ExceptionResponse exceptionResponse = new ExceptionResponse(timestamp, message, details);
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
