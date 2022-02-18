-- Таблица стран
create table if not exists countries
(
    "id"        bigserial       PRIMARY KEY,
    created     timestamp       not null,
    updated     timestamp,
    status      varchar(15)     not null,
    name        varchar(255)    not null,
    code        varchar(15)      not null
);
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
    created     timestamp       not null,
    updated     timestamp,
    status      varchar(15)     not null,
    name        varchar(255)    not null,
    code        varchar(15)     not null ,
    country_id     bigserial    not null
);
ALTER TABLE regions
    ADD CONSTRAINT fk_region_country
        FOREIGN KEY (country_id)
            REFERENCES countries("id");
comment on table regions is 'Таблица регионов';
comment on column regions.id is 'ИД региона';
comment on column regions.created is 'Дата создания региона';
comment on column regions.updated is 'Дата последнего обновления региона';
comment on column regions.status is 'Статус региона';
comment on column regions.name is 'Наименование региона';
comment on column regions.code is 'Код региона';
comment on column regions.country_id is 'ИД страны региона';

-- Таблица связи стран и регионов
create table if not exists country_regions(
    country_id      bigserial   not null,
    region_id       bigserial   not null
);
ALTER TABLE country_regions
    ADD CONSTRAINT fk_country_regions_country
        FOREIGN KEY (country_id)
            REFERENCES countries("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;
ALTER TABLE country_regions
    ADD CONSTRAINT fk_country_regions_region
        FOREIGN KEY (region_id)
            REFERENCES regions("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;
comment on table country_regions is 'Таблица связи стран и регионов';
comment on column country_regions.country_id is 'ИД страны';
comment on column country_regions.region_id is 'ИД региона';

-- Таблица населенных пунктов
create table if not exists localities(
    "id"        bigserial       PRIMARY KEY,
    created     timestamp       not null,
    updated     timestamp,
    status      varchar(15)     not null,
    name        varchar(255)    not null,
    type        varchar(255)    not null
);
comment on table localities is 'Таблица населенных пунктов';
comment on column localities.id is 'ИД населенного пункта';
comment on column localities.created is 'Дата создания населенного пункта';
comment on column localities.updated is 'Дата последнего обновления населенного пункта';
comment on column localities.status is 'Статус населенного пунктаа';
comment on column localities.name is 'Наименование населенного пунктаа';
comment on column localities.type is 'Тип населенного пунктаа';

-- Таблица адресов
create table if not exists addresses
(
    "id"        bigserial       PRIMARY KEY,
    created     timestamp       not null,
    updated     timestamp,
    status      varchar(15)     not null,
    country_id  bigserial       not null,
    region_id   bigserial       not null,
    locality_id bigserial       not null
);
ALTER TABLE addresses
    ADD CONSTRAINT fk_address_country
        FOREIGN KEY (country_id)
            REFERENCES countries("id");
ALTER TABLE addresses
    ADD CONSTRAINT fk_address_region
        FOREIGN KEY (region_id)
            REFERENCES regions("id");
ALTER TABLE addresses
    ADD CONSTRAINT fk_address_locality
        FOREIGN KEY (locality_id)
            REFERENCES localities("id");
comment on table addresses is 'Таблица адресов';
comment on column addresses.id is 'ИД адреса';
comment on column addresses.created is 'Дата создания адреса';
comment on column addresses.updated is 'Дата последнего обновления адреса';
comment on column addresses.status is 'Статус адреса';
comment on column addresses.country_id is 'ИД страны';
comment on column addresses.region_id is 'ИД региона (внутри страны)';
comment on column addresses.locality_id is 'ИД населенного пункта';