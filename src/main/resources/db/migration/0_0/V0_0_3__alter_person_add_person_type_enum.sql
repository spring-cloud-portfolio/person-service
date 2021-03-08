create type person_type_enum as enum ('COACH', 'ATHLETE', 'REFEREE');
alter table if exists person
    add column if not exists person_types person_type_enum[];
create index if not exists person_person_type_index on person (person_types);
