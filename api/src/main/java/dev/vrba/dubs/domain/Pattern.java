package dev.vrba.dubs.domain;

import io.micronaut.core.annotation.NonNull;

public record Pattern(
        @NonNull String name,
        @NonNull String emoji,
        int points,
        boolean rare
) {
}
