package dev.vrba.dubs.domain;


import io.micronaut.core.annotation.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Getter
@Entity(name = "patterns")
@NoArgsConstructor
@AllArgsConstructor
public class Pattern {
    @Id
    @NonNull
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NonNull
    @Column(name = "emoji", nullable = false)
    private String emoji;

    @NonNull
    @Column(name = "points", nullable = false)
    private BigInteger points;

    @NonNull
    @Column(name = "is_rare", nullable = false)
    private Boolean rare;
}
