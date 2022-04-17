insert into countries(id, created, updated, status, name, code)
    values
        (nextval('countries_id_seq'), now(), null, 'ACTIVE', 'Российская Федерация', 'RUS'),
        (nextval('countries_id_seq'), now(), null, 'ACTIVE', 'Княжество Андорра', 'AND'),
        (nextval('countries_id_seq'), now(), null, 'ACTIVE', 'Республика Вануату', 'VUT'),
        (nextval('countries_id_seq'), now(), null, 'ACTIVE', 'Республика Куба', 'CUB'),
        (nextval('countries_id_seq'), now(), null, 'ACTIVE', 'Королевство Испания', 'ESP');

insert into regions(id, created, updated, status, name, code, country_id)
    values
        (nextval('regions_id_seq'), now(), null, 'ACTIVE', 'Тульская область', '71', (select id from countries where countries.name = 'Российская Федерация' and countries.code = 'RUS')),
        (nextval('regions_id_seq'), now(), null, 'ACTIVE', 'Орловская область', '57', (select id from countries where countries.name = 'Российская Федерация' and countries.code = 'RUS')),
        (nextval('regions_id_seq'), now(), null, 'ACTIVE', 'Красноярский край', '24', (select id from countries where countries.name = 'Российская Федерация' and countries.code = 'RUS')),
        (nextval('regions_id_seq'), now(), null, 'ACTIVE', 'Новгородская область', '53', (select id from countries where countries.name = 'Российская Федерация' and countries.code = 'RUS')),
        (nextval('regions_id_seq'), now(), null, 'ACTIVE', 'Нижегородская область', '52', (select id from countries where countries.name = 'Российская Федерация' and countries.code = 'RUS')),
        (nextval('regions_id_seq'), now(), null, 'ACTIVE', 'Калининградская область', '39', (select id from countries where countries.name = 'Российская Федерация' and countries.code = 'RUS'));

insert into localities(id, created, updated, status, name, type, region_id)
    values
        (nextval('localities_id_seq'), now(), null, 'ACTIVE', 'Тула', 'CITY', (select id from regions where regions.name = 'Тульская область' and regions.code = '71')),
        (nextval('localities_id_seq'), now(), null, 'ACTIVE', 'Замарино', 'COUNTRY', (select id from regions where regions.name = 'Тульская область' and regions.code = '71')),
        (nextval('localities_id_seq'), now(), null, 'ACTIVE', 'Орел', 'CITY', (select id from regions where regions.name = 'Орловская область' and regions.code = '57')),
        (nextval('localities_id_seq'), now(), null, 'ACTIVE', 'Брянск', 'CITY', (select id from regions where regions.name = 'Орловская область' and regions.code = '57')),
        (nextval('localities_id_seq'), now(), null, 'ACTIVE', 'Красноярск', 'CITY', (select id from regions where regions.name = 'Красноярский край' and regions.code = '24')),
        (nextval('localities_id_seq'), now(), null, 'ACTIVE', 'Норильск', 'CITY', (select id from regions where regions.name = 'Красноярский край' and regions.code = '24')),
        (nextval('localities_id_seq'), now(), null, 'ACTIVE', 'Новгород', 'CITY', (select id from regions where regions.name = 'Новгородская область' and regions.code = '53')),
        (nextval('localities_id_seq'), now(), null, 'ACTIVE', 'Псков', 'CITY', (select id from regions where regions.name = 'Новгородская область' and regions.code = '53')),
        (nextval('localities_id_seq'), now(), null, 'ACTIVE', 'Нижний Новгород', 'CITY', (select id from regions where regions.name = 'Нижегородская область' and regions.code = '52')),
        (nextval('localities_id_seq'), now(), null, 'ACTIVE', 'Дзержинск', 'CITY', (select id from regions where regions.name = 'Нижегородская область' and regions.code = '52')),
        (nextval('localities_id_seq'), now(), null, 'ACTIVE', 'Калининград', 'CITY', (select id from regions where regions.name = 'Калининградская область' and regions.code = '39')),
        (nextval('localities_id_seq'), now(), null, 'ACTIVE', 'Советск', 'CITY', (select id from regions where regions.name = 'Калининградская область' and regions.code = '39'));

insert into addresses (id, created, updated, status, country_id, region_id, locality_id)
    values
        (nextval('addresses_id_seq'), now(), null, 'ACTIVE', (select id from countries where countries.name = 'Российская Федерация' and countries.code = 'RUS'), (select id from regions where name = 'Тульская область' and code = '71'), (select id from localities where name = 'Тула')),
        (nextval('addresses_id_seq'), now(), null, 'ACTIVE', (select id from countries where countries.name = 'Российская Федерация' and countries.code = 'RUS'), (select id from regions where name = 'Тульская область' and code = '71'), (select id from localities where name = 'Замарино')),
        (nextval('addresses_id_seq'), now(), null, 'ACTIVE', (select id from countries where countries.name = 'Российская Федерация' and countries.code = 'RUS'), (select id from regions where regions.name = 'Нижегородская область' and regions.code = '52'), (select id from localities where name = 'Дзержинск')),
        (nextval('addresses_id_seq'), now(), null, 'ACTIVE', (select id from countries where countries.name = 'Российская Федерация' and countries.code = 'RUS'), (select id from regions where regions.name = 'Красноярский край' and regions.code = '24'), (select id from localities where name = 'Норильск')),
        (nextval('addresses_id_seq'), now(), null, 'ACTIVE', (select id from countries where countries.name = 'Российская Федерация' and countries.code = 'RUS'), (select id from regions where regions.name = 'Калининградская область' and regions.code = '39'), (select id from localities where name = 'Советск'));