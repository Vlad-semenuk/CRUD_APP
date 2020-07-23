package com.flexsolution.kmb.kmbvsemenuk.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ErrorData errorData = new ErrorData();
        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errorData.getErrors()
                        .put(fieldError.getField(), fieldError.getDefaultMessage()));

        return new ResponseEntity<>(new CustomExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(), "Invalid arguments", errorData), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<CustomExceptionResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        CustomExceptionResponse exceptionResponse = new CustomExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.NOT_FOUND.value());
        exceptionResponse.setTime(LocalDateTime.now());
        exceptionResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidSearchCriteriaException.class)
    protected ResponseEntity<CustomExceptionResponse> handleInvalidSearchCriteriaException(
            InvalidSearchCriteriaException e) {
        CustomExceptionResponse exceptionResponse = new CustomExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setTime(LocalDateTime.now());
        exceptionResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    private ResponseEntity<CustomExceptionResponse> handleEntityAlreadyExistsException(EntityAlreadyExistException e) {
        CustomExceptionResponse exceptionResponse = new CustomExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.CONFLICT.value());
        exceptionResponse.setTime(LocalDateTime.now());
        exceptionResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class CustomExceptionResponse {
        private int status;
        private LocalDateTime time;
        private String message;
        private ErrorData data;
    }

    @Data
    private static class ErrorData {
        private final Map<String, String> errors = new HashMap<>();
    }
}
