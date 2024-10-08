package dev.vrba.dubs.metrics;

import dev.vrba.dubs.domain.Channel;
import dev.vrba.dubs.domain.Guild;
import dev.vrba.dubs.domain.User;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class ProcessedMessagesMetric {

    @NonNull
    private final MeterRegistry registry;

    public void record(
            @NonNull User user,
            @NonNull Channel channel,
            @NonNull Guild guild
    ) {
        registry.counter("discord.messages",
                Tags.of(
                        Tag.of("user.id", user.getId()),
                        Tag.of("user.name", user.getName()),
                        Tag.of("guild.id", guild.getId()),
                        Tag.of("guild.name", guild.getName()),
                        Tag.of("channel.id", channel.getId()),
                        Tag.of("channel.name", channel.getName())
                )
        ).increment();
    }
}
