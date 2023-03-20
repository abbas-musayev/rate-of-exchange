--liquibase formatted sql

--changeset Abbas:6
--create table users_authorities
create table users_authorities
(
    user_id        bigint not null
        constraint fkq3lq694rr66e6kpo2h84ad92q
            references users,
    authorities_id bigint not null
        constraint fkt25vmk46t0o0x01yo0wyx7wmf
            references authority,
    primary key (user_id, authorities_id)
);

alter table users_authorities
    owner to postgres;