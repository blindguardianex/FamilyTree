package org.brutforcer.service.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.common.exceptions.NonExistEntity;
import org.brutforcer.service.user.entity.Country;
import org.brutforcer.service.user.service.CountryService;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("unittest")
class JpaCountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    void loadWithNotExistId() {
        Country country = new Country();
        country.setId(0L);
        assertThrows(NonExistEntity.class, ()->countryService.saveOrLoad(country), "Сountry with id " + country.getId() + " not exist");
    }

    @Test
    void loadWithId(){
        Country country = new Country();
        country.setId(1L);
        Country loaded = countryService.saveOrLoad(country);
        assertEquals(country.getId(), loaded.getId());
    }

    @Test
    void loadWithNameAndCode(){
        Country country = new Country().setName("Республика Куба").setCode("CUB");
        Country loaded = countryService.saveOrLoad(country);
        assertEquals(country, loaded);
        assertNotNull(loaded.getId());
        assertEquals(loaded.getId(), 4L);
    }

    @Test
    void saving(){
        Country country = new Country().setName("Неизвестная страна").setCode("UNKNOWN");
        Country loaded = countryService.saveOrLoad(country);
        assertEquals(country, loaded);
        assertNotNull(loaded.getId());
    }
}