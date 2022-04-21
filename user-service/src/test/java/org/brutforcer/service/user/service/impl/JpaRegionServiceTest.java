package org.brutforcer.service.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.common.exceptions.NonExistEntity;
import org.brutforcer.service.user.entity.Country;
import org.brutforcer.service.user.entity.Region;
import org.brutforcer.service.user.service.CountryService;
import org.brutforcer.service.user.service.RegionService;
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
class JpaRegionServiceTest {

    @Autowired
    private RegionService regionService;

    @Test
    void loadWithNotExistId() {
        Region region = new Region();
        region.setId(0L);
        assertThrows(NonExistEntity.class, ()->regionService.saveOrLoad(region), "Region with id " + region.getId() + " not exist");
    }

    @Test
    void loadWithId(){
        Region region = new Region();
        region.setId(1L);

        Region loaded = regionService.saveOrLoad(region);
        assertEquals(region.getId(), loaded.getId());
    }

    @Test
    void loadWithNameAndCode(){
        Region region = new Region()
                .setName("Тульская область")
                .setCode("71");

        Region loaded = regionService.saveOrLoad(region);
        assertEquals(region, loaded);
        assertNotNull(loaded.getId());
        assertEquals(loaded.getId(), 1L);
    }

    @Test
    void saveWithoutCountry() {
        Region region = new Region()
                .setName("Неизвестный регион")
                .setCode("UNKNOWN");
        assertThrows(IllegalArgumentException.class, ()->regionService.saveOrLoad(region), "Cannot save region without country");
    }

    @Test
    void saved_countryWithId(){
        Country country = new Country();
        country.setId(4L);
        Region region = new Region()
                .setName("Новый регион")
                .setCode("NEW_REG")
                .setCountry(country);

        Region saved = regionService.saveOrLoad(region);
        assertEquals(region, saved);
        assertNotNull(saved.getId());

        regionService.delete(saved);
    }

    @Test
    void saved_countryWithoutId(){
        Country country = new Country()
                .setName("Королевство Испания")
                .setCode("ESP");
        Region region = new Region()
                .setName("Новый регион")
                .setCode("NEW_REG")
                .setCountry(country);

        Region saved = regionService.saveOrLoad(region);
        assertEquals(region, saved);
        assertNotNull(saved.getId());

        regionService.delete(saved);
    }
}