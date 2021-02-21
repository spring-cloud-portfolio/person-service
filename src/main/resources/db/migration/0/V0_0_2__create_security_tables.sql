/* User */
create table if not exists internal_user
(
    /* Generic data */
    id                      bigint generated by default as identity not null
        constraint internal_user_pk primary key,
    version                 bigint    default 0                     not null,
    created_at              timestamp default now()                 not null,
    updated_at              timestamp default now()                 not null,
    /* User data */
    person_id               bigint                                  not null
        constraint internal_user_person_id_fk references person (id),
    enabled                 boolean   default false                 not null,
    username                varchar(255)                            not null,
    password                varchar(255)                            not null,
    account_non_locked      boolean   default true                  not null,
    account_non_expired     boolean   default true                  not null,
    credentials_non_expired boolean   default true                  not null
);
create index if not exists internal_user_username_index on internal_user (username);
create index if not exists internal_user_person_id_index on internal_user (person_id);

/* User role */
create table if not exists internal_user_role
(
    /* Generic data */
    id          bigint generated by default as identity not null
        constraint internal_user_role_pk primary key,
    version     bigint    default 0                     not null,
    created_at  timestamp default now()                 not null,
    updated_at  timestamp default now()                 not null,
    /* User role data */
    role        varchar(255)                            not null,
    description varchar(255)
);

/* User authority */
create table if not exists internal_user_role_authority
(
    /* Generic data */
    id          bigint generated by default as identity not null
        constraint internal_user_role_authority_pk primary key,
    version     bigint    default 0                     not null,
    created_at  timestamp default now()                 not null,
    updated_at  timestamp default now()                 not null,
    /* User role data */
    authority   varchar(255)                            not null,
    description varchar(255)
);

/* User to role (ManyToMany) */
create table if not exists internal_user_to_role
(
    user_id bigint not null
        constraint internal_user_to_role_user_fk references internal_user (id),
    role_id bigint not null
        constraint internal_user_to_role_role_fk references internal_user_role (id)
);
create unique index if not exists internal_user_to_role_u_index on internal_user_to_role (user_id, role_id);

/* Role to authority (ManyToMany) */
create table if not exists internal_user_role_to_authority
(
    role_id      bigint not null
        constraint role_to_authority_role_fk references internal_user_role (id),
    authority_id bigint not null
        constraint role_to_authority_authority_fk references internal_user_role_authority (id)

);
create unique index if not exists internal_user_role_to_authority_u_index on internal_user_role_to_authority (role_id, authority_id);
