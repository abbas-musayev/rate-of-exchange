--liquibase formatted sql

--changeset Abbas:3
--create table valute
create table valute
(
    id                 bigint generated by default as identity
        primary key,
    code               varchar(255),
    created_date       timestamp,
    is_active          boolean,
    is_deleted         boolean,
    last_modified_date timestamp,
    name               varchar(255),
    nominal            varchar(255),
    value              numeric(19, 2),
    valute_type_id     bigint
        constraint fkikpfh0xbyasm90nodm8e2ggdq
            references valute_type
);

alter table valute
    owner to postgres;