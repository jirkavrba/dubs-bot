package dev.vrba.dubs.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Getter
@Entity(name = "matches")
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    @Column(name = "row_id")
    private Integer id;

    @Size(max = 64)
    @Column(name = "pattern_name", length = 64)
    private String patternName;

    @Column(name = "pattern_points")
    private Long patternPoints;

    @Column(name = "pattern_is_rare")
    private Boolean patternIsRare;

    @Size(max = 32)
    @Column(name = "user_id", length = 32)
    private String userId;

    @Size(max = 128)
    @Column(name = "user_name", length = 128)
    private String userName;

    @Size(max = 32)
    @Column(name = "channel_id", length = 32)
    private String channelId;

    @Size(max = 128)
    @Column(name = "channel_name", length = 128)
    private String channelName;

    @Size(max = 32)
    @Column(name = "guild_id", length = 32)
    private String guildId;

    @Size(max = 128)
    @Column(name = "guild_name", length = 128)
    private String guildName;

    @Column(name = "count")
    private BigInteger count;
}
