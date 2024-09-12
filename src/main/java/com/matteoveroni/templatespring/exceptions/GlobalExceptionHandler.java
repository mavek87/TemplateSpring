package com.matteoveroni.templatespring.exceptions;

import com.matteoveroni.templatespring.domain.dto.ErrorResponseDTO;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ErrorResponseException.class)
    public ResponseEntity<ErrorResponseDTO> handle(ErrorResponseException ex, WebRequest webRequest) {
        HttpStatusCode exStatusCode = ex.getStatusCode();

        ErrorResponseDTO errorResponseData = new ErrorResponseDTO(
                webRequest.getDescription(false),
                exStatusCode,
                exStatusCode.value(),
                ex.getBody().getDetail(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseData, exStatusCode);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handle(NoResourceFoundException ex, WebRequest webRequest) {
        HttpStatusCode exStatusCode = ex.getStatusCode();

        ErrorResponseDTO errorResponseData = new ErrorResponseDTO(
                webRequest.getDescription(false),
                exStatusCode,
                exStatusCode.value(),
                ex.getBody().getDetail(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseData, exStatusCode);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handle(Exception ex, WebRequest webRequest) {
        ErrorResponseDTO errorResponseData = new ErrorResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseData, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}