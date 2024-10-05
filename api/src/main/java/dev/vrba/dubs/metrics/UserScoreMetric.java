package dev.vrba.dubs.metrics;

import dev.vrba.dubs.domain.Score;
import dev.vrba.dubs.repository.ScoreRepository;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.MultiGauge;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;

import java.util.stream.Collectors;

@Singleton
public class UserScoreMetric {

    @NonNull
    private final MultiGauge gauge;

    @NonNull
    private final ScoreRepository repository;

    public UserScoreMetric(
            final @NonNull MeterRegistry registry,
            final @NonNull ScoreRepository repository
    ) {
        this.gauge = MultiGauge.builder("users.score").register(registry);
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

    private MultiGauge.Row<Number> mapPatternToGaugeRow(final @NonNull Score score) {
        return MultiGauge.Row.of(
                Tags.of(
                        Tag.of("user.id", score.getUserId()),
                        Tag.of("user.name", score.getUserName()),
                        Tag.of("channel.id", score.getChannelId()),
                        Tag.of("channel.name", score.getChannelName()),
                        Tag.of("guild.id", score.getGuildId()),
                        Tag.of("guild.name", score.getGuildName())
                ),
                score.getScore()
        );
    }
}
