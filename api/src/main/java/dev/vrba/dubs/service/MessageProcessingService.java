package dev.vrba.dubs.service;

import dev.vrba.dubs.domain.*;
import dev.vrba.dubs.dto.ChannelDto;
import dev.vrba.dubs.dto.GuildDto;
import dev.vrba.dubs.dto.UserDto;
import dev.vrba.dubs.repository.MatchedPatternRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

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
    @Transactional
    public List<Pattern> processMessage(
            final @NonNull String message,
            final @NonNull UserDto user,
            final @NonNull ChannelDto channel,
            final @NonNull GuildDto guild
    ) {
        final Guild guildEntity = guildService.upsertGuild(guild.id(), guild.name(), guild.icon());
        final User userEntity = userService.upsertUser(user.id(), user.name(), user.avatar());
        final Channel channelEntity = channelService.upsertChannel(channel.id(), channel.name(), guildEntity);
        final List<Pattern> patterns = patternMatchingService.matchMessagePatterns(message);

        if (!patterns.isEmpty()) {
            final List<MatchedPattern> mappedPatterns = patterns.stream()
                    .map(pattern -> new MatchedPattern(
                            0L,
                            channelEntity.getId(),
                            userEntity.getId(),
                            pattern.getName(),
                            pattern.getPoints(),
                            pattern.isRare()
                    ))
                    .toList();

            matchedPatternRepository.saveAll(mappedPatterns);

            return patterns;
        }

        return Collections.emptyList();
    }
}
