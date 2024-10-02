create table if not exists matched_patterns
(
    id         bigint      not null generated always as identity primary key,
    pattern_id varchar(32) not null,
    channel_id varchar(32) not null,
    user_id    varchar(32) not null,
    count      bigint default 0,

    constraint fk_matched_patterns_pattern_id foreign key (pattern_id) references patterns (id),
    constraint fk_matched_patterns_channel_id foreign key (channel_id) references channels (id),
    constraint fk_matched_patterns_user_id foreign key (user_id) references users (id)
);

create index on matched_patterns (pattern_id, channel_id, user_id);
