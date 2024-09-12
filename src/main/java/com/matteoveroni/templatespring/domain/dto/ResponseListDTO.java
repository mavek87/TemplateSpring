package com.matteoveroni.templatespring.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ResponseListDTO<T>(
        @JsonProperty("results") List<T> results,
        @JsonProperty("count") Integer count
) {
    public ResponseListDTO(List<T> results) {
        this(results, results != null ? results.size() : 0);
    }
}