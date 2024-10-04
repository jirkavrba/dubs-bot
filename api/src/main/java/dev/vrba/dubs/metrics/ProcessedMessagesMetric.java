package dev.vrba.dubs.metrics;

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
            @NonNull String user,
            @NonNull String channel,
            @NonNull String guild
    ) {
        registry.counter("discord.messages.processes",
                Tags.of(
                        Tag.of("guild", guild),
                        Tag.of("channel", channel),
                        Tag.of("user", user)
                )
        ).increment();
    }
}
