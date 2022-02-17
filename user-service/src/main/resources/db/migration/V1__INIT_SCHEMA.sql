-- Таблица основного профиля
create table if not exists users(
    "id"        bigserial       PRIMARY KEY,
    created     timestamp       not null,
    updated     timestamp,
    status      varchar(15)     not null,
    username    varchar(255)    not null unique ,
    password    varchar(255)    not null,
    first_name  varchar(255)    not null,
    last_name   varchar(255)    not null,
    other_name  varchar(255)    not null,
    email       varchar(255)    not null unique,
    phone_number    varchar(15) unique  unique
);
comment on table users is 'Таблица пользователей';
comment on column users.id is 'ИД пользователя';
comment on column users.created is 'Дата создания пользователя';
comment on column users.updated is 'Дата последнего обновления пользователя';
comment on column users.status is 'Статус пользователя';
comment on column users.username is 'Логин пользователя';
comment on column users.password is 'Пароль пользователя';
comment on column users.first_name is 'Имя пользователя';
comment on column users.last_name is 'Фамилия пользователя';
comment on column users.other_name is 'Отчество пользователя';
comment on column users.email is 'Адрес электронной почты пользователя';
comment on column users.phone_number is 'Номер телефона пользователя';


-- Таблица ролей
create table if not exists roles(
    "id"		bigserial	    PRIMARY KEY,
    created 	timestamp       not null,
    updated		timestamp,
    status	    varchar(15)    not null,
    "name"	    varchar(255)   not null unique
);
comment on table roles is 'Роли пользователей';
comment on column roles.id is 'ИД роли';
comment on column roles.created is 'Дата создания роли';
comment on column roles.updated is 'Дата последнего обновления роли';
comment on column roles.status is 'Статус роли';
comment on column roles.name is 'Имя роли';

-- Таблица связи пользователей и ролей
create table if not exists user_roles(
    user_id     bigserial   not null,
    role_id     bigserial   not null
);
ALTER TABLE user_roles
    ADD CONSTRAINT fk_user_roles_user
        FOREIGN KEY (user_id)
            REFERENCES users("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;
ALTER TABLE user_roles
    ADD CONSTRAINT fk_user_roles_role
        FOREIGN KEY (role_id)
            REFERENCES roles("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;
comment on table user_roles is 'Таблица связи пользователей и ролей';
comment on column user_roles.user_id is 'ИД пользователя';
comment on column user_roles.role_id is 'ИД роли';