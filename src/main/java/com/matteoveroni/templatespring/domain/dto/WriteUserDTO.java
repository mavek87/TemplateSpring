package com.matteoveroni.templatespring.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record WriteUserDTO(
        @JsonProperty("username")
        @NotEmpty(message = "Name cannot be null or empty")
        @Size(min = 6, max = 30, message = "The length of the username must be between {min} and {max}")
        String username,
        @JsonProperty("age")
        @Positive(message = "Age must be positive")
        Integer age
) {
}