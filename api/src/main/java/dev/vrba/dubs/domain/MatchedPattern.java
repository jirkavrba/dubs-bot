package dev.vrba.dubs.domain;

import io.micronaut.core.annotation.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Getter
@Entity(name = "matched_patterns")
@NoArgsConstructor
@AllArgsConstructor
public class MatchedPattern {
    @Id
    @NonNull
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private long id = 0;

    @NonNull
    @Column(name = "channel_id", nullable = false)
    private String channel;

    @NonNull
    @Column(name = "user_id", nullable = false)
    private String user;

    @NonNull
    @Column(name = "pattern_name", nullable = false)
    private String patternName;

    @NonNull
    @Column(name = "pattern_points", nullable = false)
    private long patternPoints;

    @NonNull
    @Column(name = "pattern_is_rare", nullable = false)
    private boolean patternIsRare;
}
