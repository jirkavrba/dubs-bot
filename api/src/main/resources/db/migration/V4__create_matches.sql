create or replace view matches as
(
select patterns.id      as pattern_id,
       patterns.name    as pattern_name,
       patterns.points  as pattern_points,
       patterns.is_rare as pattern_is_rare,
       users.id         as user_id,
       users.name       as user_name,
       channels.id      as channel_id,
       channels.name    as channel_name,
       guilds.id        as guild_id,
       guilds.name      as guild_name,
       count(*)         as matches_count
from matched_patterns
         left join patterns on matched_patterns.pattern_id = patterns.id
         left join users on matched_patterns.user_id = users.id
         left join channels on matched_patterns.channel_id = channels.id
         left join guilds on channels.guild_id = guilds.id
group by patterns.id,
         users.id,
         channels.id,
         guilds.id
    );
