package dev.vrba.dubs.service;

import dev.vrba.dubs.domain.Guild;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;

import java.util.HashSet;

@Singleton
public class GuildService {

    @NonNull
    public Guild upsertGuild(
            @NonNull String id,
            @NonNull String name,
            @NonNull String icon
    ) {
        return new Guild(id, name, icon, new HashSet<>());
    }
}
