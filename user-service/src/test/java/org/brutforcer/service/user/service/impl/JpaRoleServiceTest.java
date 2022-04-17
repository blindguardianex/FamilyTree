package org.brutforcer.service.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.common.exceptions.UpdatingNonExistEntity;
import org.brutforcer.service.user.entity.Role;
import org.brutforcer.service.user.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("unittest")
class JpaRoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    void addAndDelete() {
        var testRole = new Role().setName("TEST_ROLE");
        var savedRole = roleService.add(testRole);
        assertEquals(testRole, savedRole);
        log.info("Saved role: {}", savedRole);
        roleService.delete(savedRole);
        var deletedRole = roleService.getById(savedRole.getId());
        assertTrue(deletedRole.isEmpty());
    }

    @Test
    void getAll() {
        var all = roleService.getAll();
        all.forEach(role -> log.info("Founded role: {}", role.toString()));
    }

    @Test
    void getById() {
        final long existingId = 1L;
        final long notExistingId = 0L;

        var role = roleService.getById(existingId);
        assertTrue(role.isPresent());

        role = roleService.getById(notExistingId);
        assertTrue(role.isEmpty());
    }

    @Test
    void update() {
        assertThrows(UpdatingNonExistEntity.class, ()->roleService.update(new Role()));
        final Role finalRole = new Role();
        finalRole.setId(0L);
        assertThrows(UpdatingNonExistEntity.class, ()->roleService.update(finalRole));

        final String existingName = "SYSTEM";

        Role role = roleService.getByName(existingName).get();
        assertEquals(existingName, role.getName());

        final String newName = "SYSTEM_new";
        role = roleService.update(role.setName(newName));
        assertEquals(newName, role.getName());
        role = roleService.getByName(newName).get();
        assertEquals(newName, role.getName());

        role = roleService.update(role.setName(existingName));
        assertEquals(existingName, role.getName());
        role = roleService.getByName(existingName).get();
        assertEquals(existingName, role.getName());
    }

    @Test
    void getByName() {
        final String existingName = "SYSTEM";
        final String notExistingName = "ABRA-KADABRA";

        var role = roleService.getByName(existingName);
        assertTrue(role.isPresent());

        role = roleService.getByName(notExistingName);
        assertTrue(role.isEmpty());
    }
}