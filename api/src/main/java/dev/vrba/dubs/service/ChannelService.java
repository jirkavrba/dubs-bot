package dev.vrba.dubs.service;

import dev.vrba.dubs.domain.Channel;
import dev.vrba.dubs.domain.Guild;
import dev.vrba.dubs.repository.ChannelRepository;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class ChannelService {

    @NonNull
    private final ChannelRepository repository;

    @NonNull
    public Channel upsertChannel(
            @NonNull String id,
            @NonNull String name,
            @NonNull Guild guild
    ) {
        final Optional<Channel> existingGuild = repository.findById(id);
        return existingGuild
                .map(entity -> repository.update(entity.withName(name).withGuild(guild)))
                .orElseGet(() -> repository.save(new Channel(id, name, guild)));
    }
}
