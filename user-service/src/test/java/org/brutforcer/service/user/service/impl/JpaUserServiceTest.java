package org.brutforcer.service.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.service.user.entity.*;
import org.brutforcer.service.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("unittest")
class JpaUserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void add() {
        var country = new Country().setName("Russian Federation").setCode("RF");
        var region = new Region().setName("Tulskaya oblast").setCode("71").setCountry(country);
        var locality = new Locality().setName("Aleksin").setType(Locality.Type.CITY).setRegion(region);
        var add = userService.add(
                new User()
                        .setUsername("TEST_login")
                        .setPassword("TEST_pass")
                        .setProfile(
                                new UserProfile()
                                        .setFirstName("TEST_first")
                                        .setLastName("TEST_last")
                                        .setOtherName("TEST_other")
                                        .setEmail("TEST_1@mail.ru")
                                        .setBirthDate(LocalDate.now())
                                        .setBirthPlace(
                                                new Address()
                                                        .setCountry(country)
                                                        .setRegion(region)
                                                        .setLocality(locality)
                                        )
                        )
        );
        System.out.println(add);
    }

    @Test
    void getByUsername() {
        var user = userService.getByUsername("TEST_login");
        System.out.println(user.get());
    }
}