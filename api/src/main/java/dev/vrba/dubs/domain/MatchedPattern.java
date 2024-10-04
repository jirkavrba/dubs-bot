package dev.vrba.dubs.domain;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedEntity("matched_patterns")
public class MatchedPattern {
    @Id
    @NonNull
    @GeneratedValue()
    @MappedProperty("id")
    private Long id = null;

    @NonNull
    @MappedProperty("channel_id")
    private String channel;

    @NonNull
    @MappedProperty("user_id")
    private String user;

    @NonNull
    @MappedProperty("pattern_name")
    private String patternName;

    @NonNull
    @MappedProperty("pattern_points")
    private long patternPoints;

    @NonNull
    @MappedProperty("pattern_is_rare")
    private boolean patternIsRare;
}
