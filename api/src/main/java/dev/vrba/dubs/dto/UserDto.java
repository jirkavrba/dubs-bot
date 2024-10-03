package dev.vrba.dubs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record UserDto(
        @NotBlank
        @JsonProperty("id")
        String id,

        @NotBlank
        @JsonProperty("name")
        String name,

        @NotBlank
        @JsonProperty("avatar_url")
        String avatar
) {
}
