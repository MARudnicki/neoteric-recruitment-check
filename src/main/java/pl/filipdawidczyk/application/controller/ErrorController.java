package pl.filipdawidczyk.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.filipdawidczyk.application.exception.WrongRequestException;
import pl.filipdawidczyk.application.model.Error;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(WrongRequestException.class)
    public ResponseEntity<Error> handleWrongRequestException(final WrongRequestException exception) {
        return ResponseEntity
                .badRequest()
                .body(Error.of(exception.getMessage()));
    }
}
