--liquibase formatted sql

--changeset Abbas:5
--create table users
create table users
(
    id                         bigint generated by default as identity
        primary key,
    email                      varchar(255),
    is_account_non_expired     boolean not null,
    is_account_non_locked      boolean not null,
    is_credentials_non_expired boolean not null,
    is_enabled                 boolean not null,
    password                   varchar(255),
    username                   varchar(255)
);

alter table users
    owner to postgres;