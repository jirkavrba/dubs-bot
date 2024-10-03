package dev.vrba.dubs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Introspected
public record MatchedPatternDto(
        @JsonProperty("name") String name,
        @JsonProperty("emoji") String emoji,
        @JsonProperty("is_rare") boolean rare
) {
}
