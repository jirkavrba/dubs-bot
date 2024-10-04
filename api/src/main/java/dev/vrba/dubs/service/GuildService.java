package dev.vrba.dubs.service;

import dev.vrba.dubs.domain.Guild;
import dev.vrba.dubs.repository.GuildRepository;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class GuildService {

    @NonNull
    private final GuildRepository repository;

    @NonNull
    public Guild upsertGuild(
            @NonNull String id,
            @NonNull String name,
            @NonNull String icon
    ) {
        final Optional<Guild> existingGuild = repository.findById(id);
        return existingGuild
                .map(entity -> repository.update(entity.withName(name).withIcon(icon)))
                .orElseGet(() -> repository.save(new Guild(id, name, icon)));
    }
}
