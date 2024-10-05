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
@MappedEntity("score")
public class Score {
    @Id
    @MappedProperty("row_id")
    private Integer id;

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

    @MappedProperty("score")
    private BigInteger score;
}
