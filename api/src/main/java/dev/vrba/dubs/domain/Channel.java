package dev.vrba.dubs.domain;

import io.micronaut.core.annotation.NonNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "channels")
@NoArgsConstructor
@AllArgsConstructor
public class Channel {
    @Id
    @NonNull
    @Column(name = "id", nullable = false)
    private String id;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @JoinColumn(name = "guild_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Guild guild;
}
