create table if not exists users
(
    id         varchar(32)  not null primary key,
    name       varchar(128) not null unique,
    avatar_url varchar(256) not null,
    points     bigint       not null default 0
);

create index on users (points);
