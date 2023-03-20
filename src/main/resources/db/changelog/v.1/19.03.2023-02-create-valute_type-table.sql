--liquibase formatted sql

--changeset Abbas:2
--create table valute_type
create table valute_type
(
    id                 bigint generated by default as identity
        primary key,
    created_date       timestamp,
    is_active          boolean,
    is_deleted         boolean,
    last_modified_date timestamp,
    type               varchar(255),
    valute_curs_id     bigint
        constraint fkootyp0johxk16itm845sc3n0c
            references valute_curs
);

alter table valute_type
    owner to postgres;
