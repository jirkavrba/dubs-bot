package dev.vrba.dubs.domain;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedEntity("matches")
public class Match {
    @Id
    @MappedProperty("row_id")
    private Integer id;

    @MappedProperty("pattern_name")
    private String patternName;

    @MappedProperty("pattern_points")
    private Long patternPoints;

    @MappedProperty("pattern_is_rare")
    private Boolean patternIsRare;

    @MappedProperty("user_id")
    private String userId;

    @MappedProperty("user_name")
    private String userName;

    @MappedProperty("channel_id")
    private String channelId;

    @MappedProperty("channel_name")
    private String channelName;

    @MappedProperty("guild_id")
    private String guildId;

    @MappedProperty("guild_name")
    private String guildName;

    @MappedProperty("count")
    private BigInteger count;

    @MappedProperty("points")
    private BigInteger points;
}
