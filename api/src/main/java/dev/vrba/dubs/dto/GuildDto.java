package dev.vrba.dubs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Serdeable
@Introspected
public record GuildDto(
        @NotNull
        @NotBlank
        @JsonProperty("id")
        String id,

        @NotNull
        @NotBlank
        @JsonProperty("name")
        String name,

        @NotNull
        @NotBlank
        @JsonProperty("icon_url")
        String icon
) {
}
