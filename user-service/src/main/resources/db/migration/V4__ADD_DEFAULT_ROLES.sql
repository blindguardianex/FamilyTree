insert into roles (created, updated, status, name)
    values
        (now(), null, 'ACTIVE', 'ADMIN'),
        (now(), null, 'ACTIVE', 'USER'),
        (now(), null, 'ACTIVE', 'ADVANCED_USER')