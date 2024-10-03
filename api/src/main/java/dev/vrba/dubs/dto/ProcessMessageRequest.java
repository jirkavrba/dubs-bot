package dev.vrba.dubs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.NonNull;

public record ProcessMessageRequest(
        @NonNull @JsonProperty("message_id") String message,
        @NonNull @JsonProperty("user") UserDto user,
        @NonNull @JsonProperty("channel") ChannelDto channel,
        @NonNull @JsonProperty("guild") GuildDto guild
) {
}
