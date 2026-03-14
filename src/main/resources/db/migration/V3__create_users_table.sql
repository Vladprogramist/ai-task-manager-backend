CREATE TABLE users (
    id bigserial primary key,
    email VARCHAR(255) not null unique,
    password_hash VARCHAR(255) not null,
    created_at timestamp not null default now()
);