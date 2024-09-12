package com.matteoveroni.templatespring.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReadUserDTO(
        @JsonProperty("id") Long id,
        @JsonProperty("username") String username,
        @JsonProperty("age") Integer age
) {
}