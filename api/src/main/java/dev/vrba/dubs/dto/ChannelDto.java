package dev.vrba.dubs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record ChannelDto(
        @NotBlank
        @JsonProperty("id")
        String id,

        @NotBlank
        @JsonProperty("name")
        String name
) {
}
