package dev.vrba.dubs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
@Introspected
public record ProcessMessageResponse(
        @JsonProperty("matches") boolean matches,
        @JsonProperty("patterns") List<MatchedPatternDto> patterns
) {
    @JsonProperty("is_rare")
    public boolean rare() {
        return patterns.stream().anyMatch(MatchedPatternDto::rare);
    }
}
