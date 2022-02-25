package org.brutforcer.service.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.service.user.entity.Role;
import org.brutforcer.service.user.enums.Status;
import org.brutforcer.service.user.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("unittest")
class JpaRoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    void add() {
        var testRole = roleService.add(new Role().setName("TEST_ROLE"));
        System.out.println(testRole);
    }

    @Test
    void getAll() {
        var all = roleService.getAll();
        all.forEach(System.out::println);
    }

    @Test
    void getById() {

    }
}