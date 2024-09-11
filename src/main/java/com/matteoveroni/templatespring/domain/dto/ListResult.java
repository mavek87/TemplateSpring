package com.matteoveroni.templatespring.domain.dto;

import java.util.List;

public record ListResult<T>(List<T> results) {
}