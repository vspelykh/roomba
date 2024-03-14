package com.andersenlab.roomba.exception;

import com.andersenlab.roomba.model.response.ResponseExceptionBody;
import lombok.NonNull;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  @NonNull WebRequest request) {

        String message = Optional.of((ex).getBindingResult())
                .map(Errors::getAllErrors)
                .stream()
                .flatMap(Collection::stream)
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .reduce((message1, message2) -> message1 + ", " + message2)
                .orElse("Invalid request");

        ResponseExceptionBody exceptionBody = new ResponseExceptionBody(status.value(), message);
        return new ResponseEntity<>(exceptionBody, HttpStatus.BAD_REQUEST);
    }
}