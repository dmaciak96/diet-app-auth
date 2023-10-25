CREATE TABLE IF NOT EXISTS authority
(
    id                  uuid PRIMARY KEY UNIQUE,
    application_user_id uuid         NOT NULL,
    username            varchar(255) NOT NULL,
    role                varchar(255),
    foreign key (application_user_id) references application_user (id)
);