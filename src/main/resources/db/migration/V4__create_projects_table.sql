CREATE TABLE projects (
    id bigserial primary key,
    user_id bigint not null,
    title varchar(255) not null,
    description text,
    deadline timestamp,
    status varchar(50) not null,
    priority varchar(50) not null,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),

    constraint fk_projects_user foreign key (user_id) references users(id)
);