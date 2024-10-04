package dev.vrba.dubs.domain;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.*;

@With
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedEntity("guilds")
public class Guild {
    @Id
    @NonNull
    @MappedProperty("id")
    private String id;

    @NonNull
    @MappedProperty("name")
    private String name;

    @NonNull
    @MappedProperty("icon_url")
    private String icon;
}
