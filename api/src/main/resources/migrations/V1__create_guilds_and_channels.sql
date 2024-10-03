create table if not exists guilds
(
    id       varchar(32)  not null primary key,
    name     varchar(128) not null,
    icon_url varchar(256) not null
);

create table if not exists channels
(
    id       varchar(32)  not null primary key,
    name     varchar(128) not null,
    guild_id varchar(32)  not null,

    constraint fk_channels_guild_id foreign key (guild_id) references guilds (id) on delete cascade
);

create index on channels (guild_id);
