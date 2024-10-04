package dev.vrba.dubs.metrics;

import dev.vrba.dubs.domain.Match;
import dev.vrba.dubs.repository.MatchRepository;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.MultiGauge;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;

import java.util.stream.Collectors;

@Singleton
public class MatchesMetricConfiguration {

    @NonNull
    private final MultiGauge gauge;

    @NonNull
    private final MatchRepository repository;

    public MatchesMetricConfiguration(
            final @NonNull MeterRegistry registry,
            final @NonNull MatchRepository repository
    ) {
        this.gauge = MultiGauge.builder("pattern.matches").register(registry);
        this.repository = repository;
    }

    @Scheduled(fixedRate = "PT1M")
    public void refresh() {
        gauge.register(
                repository.findAll()
                        .stream()
                        .map(this::mapPatternToGaugeRow)
                        .collect(Collectors.toList()),
                true
        );
    }

    private MultiGauge.Row<Number> mapPatternToGaugeRow(final @NonNull Match match) {
        return MultiGauge.Row.of(
                Tags.of(
                        Tag.of("user.id", match.getUserId()),
                        Tag.of("user.name", match.getUserName()),
                        Tag.of("channel.id", match.getChannelId()),
                        Tag.of("channel.name", match.getChannelName()),
                        Tag.of("guild.id", match.getGuildId()),
                        Tag.of("guild.name", match.getGuildName()),
                        Tag.of("pattern.name", match.getPatternName()),
                        Tag.of("pattern.rare", match.getPatternIsRare().toString()),
                        Tag.of("pattern.points", match.getPatternPoints().toString())
                ),
                match.getCount()
        );
    }
}
