package com.matteoveroni.templatespring.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@Data
public class ErrorResponseDTO {

    @JsonProperty("api_path")
    private String apiPath;
    @JsonProperty("error_code_name")
    private HttpStatusCode errorCodeName;
    @JsonProperty("error_code")
    private int errorCode;
    @JsonProperty("error_message")
    private String errorMessage;
    @JsonProperty("error_time")
    private LocalDateTime errorTime;
}
