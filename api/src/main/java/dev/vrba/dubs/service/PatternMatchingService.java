package dev.vrba.dubs.service;

import dev.vrba.dubs.domain.Pattern;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class PatternMatchingService {

    @NonNull
    public List<Pattern> matchMessagePatterns(@NonNull String id) {
        return List.of();
    }
}
