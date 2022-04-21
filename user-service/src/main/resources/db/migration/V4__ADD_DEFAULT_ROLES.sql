insert into roles (id, created, updated, status, name)
    values
        (nextval('roles_id_seq'), now(), null, 'ACTIVE', 'ADMIN'),
        (nextval('roles_id_seq'), now(), null, 'ACTIVE', 'SYSTEM'),
        (nextval('roles_id_seq'), now(), null, 'ACTIVE', 'USER'),
        (nextval('roles_id_seq'), now(), null, 'ACTIVE', 'ADVANCED_USER');