create table if not exists patterns
(
    id      varchar(32)  not null primary key,
    name    varchar(128) not null,
    emoji   varchar(16)  not null,
    points  bigint       not null default 0,
    is_rare boolean      not null default false
);
