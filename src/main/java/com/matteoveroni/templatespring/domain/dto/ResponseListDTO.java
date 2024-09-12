package com.matteoveroni.templatespring.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ResponseListDTO<T>(@JsonProperty("results") List<T> results) {
}