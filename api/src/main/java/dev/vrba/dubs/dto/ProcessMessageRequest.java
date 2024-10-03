package dev.vrba.dubs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Serdeable
@Introspected
public record ProcessMessageRequest(
        @NotNull
        @NotBlank
        @JsonProperty("message_id")
        String message,

        @NotNull
        @JsonProperty("user")
        UserDto user,

        @NotNull
        @JsonProperty("channel")
        ChannelDto channel,

        @NotNull
        @JsonProperty("guild")
        GuildDto guild
) {
}
