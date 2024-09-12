package com.matteoveroni.templatespring.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WriteUserDTO(
        @JsonProperty("username") String username,
        @JsonProperty("age") Integer age
) {
}