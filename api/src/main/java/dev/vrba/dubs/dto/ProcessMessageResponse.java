package dev.vrba.dubs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ProcessMessageResponse(
        @JsonProperty("matches") boolean matches,
        @JsonProperty("patterns") List<MatchedPatternDto> patterns
) {
    @JsonProperty("is_rare")
    public boolean rare() {
        return patterns.stream().anyMatch(MatchedPatternDto::rare);
    }
}
