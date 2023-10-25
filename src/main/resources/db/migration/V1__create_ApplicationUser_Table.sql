CREATE TABLE IF NOT EXISTS application_user
(
    id         uuid PRIMARY KEY UNIQUE,
    username   varchar(255) UNIQUE,
    password   varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    enabled    boolean,
    version    int,
    email      varchar(255),
    avatar     bytea
);