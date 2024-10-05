drop view if exists matches;
create or replace view matches as
(
select row_number() over (order by users.id) as row_id,
       pattern_name,
       pattern_points,
       pattern_is_rare,
       users.id                              as user_id,
       users.name                            as user_name,
       channels.id                           as channel_id,
       channels.name                         as channel_name,
       guilds.id                             as guild_id,
       guilds.name                           as guild_name,
       count(*)                              as count,
       (count(*) * pattern_points)              as points
from matched_patterns
         left join users on matched_patterns.user_id = users.id
         left join channels on matched_patterns.channel_id = channels.id
         left join guilds on channels.guild_id = guilds.id
group by users.id,
         channels.id,
         guilds.id,
         pattern_name,
         pattern_points,
         pattern_is_rare
    );
