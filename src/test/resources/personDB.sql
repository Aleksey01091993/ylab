create table if not exists users(
    id BIGINT primary key generated always as identity,
    name varchar(55),
    email varchar(55) unique,
    password varchar(55)
);