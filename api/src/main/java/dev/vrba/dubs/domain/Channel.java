package dev.vrba.dubs.domain;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.data.annotation.sql.JoinColumn;
import lombok.*;

@With
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedEntity("channels")
public class Channel {
    @Id
    @NonNull
    @MappedProperty("id")
    private String id;

    @NonNull
    @MappedProperty("name")
    private String name;

    @JoinColumn(name = "guild_id", referencedColumnName = "id")
    private Guild guild;
}
