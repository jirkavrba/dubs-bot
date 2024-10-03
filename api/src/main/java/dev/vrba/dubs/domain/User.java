package dev.vrba.dubs.domain;

import io.micronaut.core.annotation.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

import java.math.BigInteger;

@With
@Getter
@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @NonNull
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NonNull
    @Column(name = "avatar_url", nullable = false)
    private String avatar;

    @NonNull
    @Column(name = "points", nullable = false)
    private BigInteger points;
}
