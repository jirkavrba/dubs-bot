package dev.vrba.dubs.service;

import dev.vrba.dubs.domain.Pattern;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Singleton
@RequiredArgsConstructor
public class PatternMatchingService {

    @NonNull
    private final List<Pattern> patterns;

    @NonNull
    public List<Pattern> matchMessagePatterns(@NonNull String id) {
        return patterns.stream()
                .filter(pattern -> pattern.matches(id))
                .toList();
    }
}
