package com.test.technical.common.exceptions;


import com.test.technical.common.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Response> handleValidationErrors(BindException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
            .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(Response.<Object>builder().data(errors).code(422)
            .message("ERROR").build(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Response> handleConstraintsErrors(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations().stream().map(e -> e.getMessage()).collect(
            Collectors.toList());
        return new ResponseEntity<>(Response.<Object>builder().data(errors).code(422)
            .message("ERROR").build(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(DuplicatedFieldException.class)
    public ResponseEntity<Response> handleValidationErrors(DuplicatedFieldException ex) {
        return new ResponseEntity<>(Response.<Object>builder().code(ex.getErrorCode())
            .message(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Response> handleMissingParams(MissingServletRequestParameterException ex) {
        String error = ex.getMessage();
        return new ResponseEntity<>(Response.<Object>builder().data(error).code(422)
            .message("ERROR").build(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
