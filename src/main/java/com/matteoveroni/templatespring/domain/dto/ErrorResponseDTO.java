package com.matteoveroni.templatespring.domain.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@Data
public class ErrorResponseDTO {

    private String apiPath;
    private HttpStatusCode errorCode;
    private String errorMessage;
    private LocalDateTime errorTime;
}
