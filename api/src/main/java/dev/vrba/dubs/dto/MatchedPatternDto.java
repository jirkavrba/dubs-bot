package dev.vrba.dubs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MatchedPatternDto(
        @JsonProperty("name") String name,
        @JsonProperty("emoji") String emoji,
        @JsonProperty("is_rare") boolean rare
) {
}
