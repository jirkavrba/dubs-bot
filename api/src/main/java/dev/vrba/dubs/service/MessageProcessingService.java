package dev.vrba.dubs.service;

import dev.vrba.dubs.domain.*;
import dev.vrba.dubs.dto.ChannelDto;
import dev.vrba.dubs.dto.GuildDto;
import dev.vrba.dubs.dto.UserDto;
import dev.vrba.dubs.metrics.MatchedPatternsMetric;
import dev.vrba.dubs.metrics.ProcessedMessagesMetric;
import dev.vrba.dubs.repository.MatchedPatternRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

@Singleton
@RequiredArgsConstructor
public class MessageProcessingService {

    @NonNull
    private final UserService userService;

    @NonNull
    private final ChannelService channelService;

    @NonNull
    private final GuildService guildService;

    @NonNull
    private final MatchedPatternRepository matchedPatternRepository;

    @NonNull
    private final PatternMatchingService patternMatchingService;

    @NonNull
    private final ProcessedMessagesMetric processedMessageMetric;

    @NonNull
    private final MatchedPatternsMetric matchedPatternsMetric;

    @NonNull
    @Transactional
    public List<Pattern> processMessage(final @NonNull String message, final @NonNull UserDto user, final @NonNull ChannelDto channel, final @NonNull GuildDto guild) {
        final Guild guildEntity = guildService.upsertGuild(guild.id(), guild.name(), guild.icon());
        final User userEntity = userService.upsertUser(user.id(), user.name(), user.avatar());
        final Channel channelEntity = channelService.upsertChannel(channel.id(), channel.name(), guildEntity);
        final List<Pattern> patterns = patternMatchingService.matchMessagePatterns(message);

        processedMessageMetric.record(userEntity, channelEntity, guildEntity);

        if (!patterns.isEmpty()) {
            final List<MatchedPattern> mappedPatterns = patterns.stream().map(pattern -> new MatchedPattern(null, channelEntity.getId(), userEntity.getId(), pattern.getName(), pattern.getPoints(), pattern.isRare())).toList();

            final BigInteger totalPoints = patterns.stream().map(Pattern::getPoints).map(BigInteger::valueOf).reduce(BigInteger.ZERO, BigInteger::add);

            userService.incrementUserPoints(userEntity, totalPoints);
            matchedPatternRepository.saveAll(mappedPatterns);

            patterns.forEach(pattern -> {
                matchedPatternsMetric.record(
                        userEntity,
                        channelEntity,
                        guildEntity,
                        pattern
                );
            });

            return patterns;
        }

        return Collections.emptyList();
    }
}
