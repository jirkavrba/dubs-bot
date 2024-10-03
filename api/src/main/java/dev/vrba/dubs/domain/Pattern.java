package dev.vrba.dubs.domain;

import io.micronaut.core.annotation.NonNull;

public interface Pattern {
    @NonNull
    String getName();

    @NonNull
    String getEmoji();

    long getPoints();

    boolean isRare();

    boolean matches(@NonNull String id);
}
