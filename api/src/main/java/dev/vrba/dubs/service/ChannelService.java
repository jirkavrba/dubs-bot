package dev.vrba.dubs.service;

import dev.vrba.dubs.domain.Channel;
import dev.vrba.dubs.domain.Guild;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;

@Singleton
public class ChannelService {

    @NonNull
    public Channel upsertChannel(
            @NonNull String id,
            @NonNull String name,
            @NonNull Guild guild
    ) {
        return new Channel(id, name, guild);
    }
}
