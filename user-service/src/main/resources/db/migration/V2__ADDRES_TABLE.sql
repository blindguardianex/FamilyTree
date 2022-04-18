-- Таблица стран
create table if not exists countries
(
    "id"        bigserial       PRIMARY KEY,
    created     timestamp       not null    default now(),
    updated     timestamp,
    status      varchar(15)     not null    default 'ACTIVE',
    name        varchar(255)    not null,
    code        varchar(15)     not null
);
create unique index country_name_code_idx on countries (name, code);

comment on table countries is 'Таблица стран';
comment on column countries.id is 'ИД страны';
comment on column countries.created is 'Дата создания страны';
comment on column countries.updated is 'Дата последнего обновления страны';
comment on column countries.status is 'Статус страны';
comment on column countries.name is 'Наименование страны';
comment on column countries.code is 'Код страны';

-- Таблица регионов
create table if not exists regions(
    "id"        bigserial       PRIMARY KEY,
    created     timestamp       not null    default now(),
    updated     timestamp,
    status      varchar(15)     not null    default 'ACTIVE',
    name        varchar(255)    not null,
    code        varchar(15)     not null,
    country_id     bigserial    not null
);
alter table regions add constraint fk_region_country foreign key (country_id) references countries("id");
create unique index region_name_code_idx on regions(name, code);

comment on table regions is 'Таблица регионов';
comment on column regions.id is 'ИД региона';
comment on column regions.created is 'Дата создания региона';
comment on column regions.updated is 'Дата последнего обновления региона';
comment on column regions.status is 'Статус региона';
comment on column regions.name is 'Наименование региона';
comment on column regions.code is 'Код региона';
comment on column regions.country_id is 'ИД страны региона';

-- Таблица населенных пунктов
create table if not exists localities(
    "id"        bigserial       PRIMARY KEY,
    created     timestamp       not null    default now(),
    updated     timestamp,
    status      varchar(15)     not null    default 'ACTIVE',
    name        varchar(255)    not null,
    type        varchar(255)    not null,
    region_id   bigserial       not null
);
alter table localities add constraint fk_locality_region foreign key (region_id) references regions("id");
create unique index locality_name_type_region_idx on localities(name, type, region_id);

comment on table localities is 'Таблица населенных пунктов';
comment on column localities.id is 'ИД населенного пункта';
comment on column localities.created is 'Дата создания населенного пункта';
comment on column localities.updated is 'Дата последнего обновления населенного пункта';
comment on column localities.status is 'Статус населенного пунктаа';
comment on column localities.name is 'Наименование населенного пунктаа';
comment on column localities.type is 'Тип населенного пункта';
comment on column localities.region_id is 'Регион населенного пункта';

-- Таблица адресов
create table if not exists addresses
(
    "id"        bigserial       PRIMARY KEY,
    created     timestamp       not null    default now(),
    updated     timestamp,
    status      varchar(15)     not null    default 'ACTIVE',
    country_id  bigserial       not null,
    region_id   bigserial       not null,
    locality_id bigserial       not null,
    text_address    text    unique  not null
);
alter table addresses add constraint fk_address_country foreign key (country_id) references countries("id");
alter table addresses add constraint fk_address_region foreign key (region_id) references regions("id");
alter table addresses add constraint fk_address_locality foreign key (locality_id) references localities("id");
comment on table addresses is 'Таблица адресов';
comment on column addresses.id is 'ИД адреса';
comment on column addresses.created is 'Дата создания адреса';
comment on column addresses.updated is 'Дата последнего обновления адреса';
comment on column addresses.status is 'Статус адреса';
comment on column addresses.country_id is 'ИД страны';
comment on column addresses.region_id is 'ИД региона (внутри страны)';
comment on column addresses.locality_id is 'ИД населенного пункта';
comment on column addresses.text_address is 'Уникальное строковое представление адреса';
