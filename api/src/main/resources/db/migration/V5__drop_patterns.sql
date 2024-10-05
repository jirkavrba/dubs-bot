alter table matched_patterns
    drop column pattern_id cascade,
    add column pattern_name    varchar(64) not null,
    add column pattern_points  bigint      not null default 0,
    add column pattern_is_rare boolean     not null default false;

drop table patterns;

create or replace view matches as
(
select pattern_name,
       pattern_points,
       pattern_is_rare,
       users.id      as user_id,
       users.name    as user_name,
       channels.id   as channel_id,
       channels.name as channel_name,
       guilds.id     as guild_id,
       guilds.name   as guild_name,
       count(*)      as count
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
