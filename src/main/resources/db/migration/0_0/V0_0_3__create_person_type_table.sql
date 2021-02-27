/* Person type */
create table if not exists person_type
(
    type      varchar(50) not null,
    person_id bigint      not null,
    constraint person_type_pk primary key (type, person_id)
);
