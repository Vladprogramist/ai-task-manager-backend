CREATE TABLE tasks (
    id bigserial primary key,
    project_id bigint not null,
    title varchar(255) not null,
    description text,
    status varchar(50) not null,
    priority varchar(50) not null,
    planned_date timestamp,
    estimated_minutes int,
    order_index int,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),

    constraint fk_tasks_project foreign key (project_id) references projects(id)
);