package com.buks.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.buks.util.ApplicationException.ApplicationExceptionResponseObject.from;

@RestControllerAdvice
class ApplicationExceptionAdviceHandler {

    @ExceptionHandler(ApplicationException.class)
    @ResponseBody
    private ResponseEntity<ApplicationException.ApplicationExceptionBuilder> handleEntityNotFound(ApplicationException exception) {
       ApplicationException.ApplicationExceptionResponseObject applicationExceptionBuilder = from(exception);
        return new ResponseEntity(applicationExceptionBuilder, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
