package net.katrinka.clinicalrelevance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AssayCodeNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(AssayNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String assayCodeNotFoundHandler(AssayNotFoundException exception) {
        return exception.getMessage();
    }
}
