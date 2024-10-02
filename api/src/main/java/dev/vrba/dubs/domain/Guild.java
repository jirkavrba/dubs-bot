package dev.vrba.dubs.domain;

import io.micronaut.core.annotation.NonNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Entity(name = "guilds")
@NoArgsConstructor
@AllArgsConstructor
public class Guild {
    @Id
    @NonNull
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @NonNull
    @Column(name = "icon_url", nullable = false)
    private String icon;

    @NonNull
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guild")
    private Set<Channel> channels;
}
