package dev.vrba.dubs.mapper;

import dev.vrba.dubs.domain.Pattern;
import dev.vrba.dubs.dto.MatchedPatternDto;
import dev.vrba.dubs.dto.ProcessMessageResponse;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class MatchedPatternMapper {
    @NonNull
    public ProcessMessageResponse mapMatchedPatternsToResponse(final @NonNull List<Pattern> patterns) {
        return new ProcessMessageResponse(!patterns.isEmpty(), mapMatchedPatternsToDto(patterns));
    }

    @NonNull
    public List<MatchedPatternDto> mapMatchedPatternsToDto(final @NonNull List<Pattern> patterns) {
        return patterns.stream()
                .map(this::mapMatchedPatternToDto)
                .toList();

    }

    @NonNull
    public MatchedPatternDto mapMatchedPatternToDto(final @NonNull Pattern pattern) {
        return new MatchedPatternDto(
                pattern.getName(),
                pattern.getEmoji(),
                pattern.isRare()
        );
    }
}
