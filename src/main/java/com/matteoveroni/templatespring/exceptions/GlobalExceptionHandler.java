package com.matteoveroni.templatespring.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
//    extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public void handle(Exception ex) {
//    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        Map<String, String> validationErrors = new HashMap<>();
//        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();
//
//        validationErrorList.forEach(error -> {
//            String fieldName = ((FieldError) error).getField();
//            String validationMessage = error.getDefaultMessage();
//            validationErrors.put(fieldName, validationMessage);
//        });
//
//        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
//    }
}