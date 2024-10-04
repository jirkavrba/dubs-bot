package dev.vrba.dubs.domain;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.*;

import java.math.BigInteger;

@With
@Getter
@Setter
@MappedEntity("users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @NonNull
    @MappedProperty("id")
    private String id;

    @NonNull
    @MappedProperty("name")
    private String name;

    @NonNull
    @MappedProperty("avatar_url")
    private String avatar;

    @NonNull
    @MappedProperty("points")
    private BigInteger points;
}
