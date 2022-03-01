alter table users add column birth_place bigserial not null;
alter table users add constraint fk_user_birth_place foreign key (birth_place) references addresses("id");

alter table users add column birth_date date not null default now();
alter table users alter column birth_date drop default;