package org.brutforcer.service.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.common.exceptions.NonExistEntity;
import org.brutforcer.service.user.entity.Locality;
import org.brutforcer.service.user.entity.Region;
import org.brutforcer.service.user.service.LocalityService;
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
class JpaLocalityServiceTest {

    @Autowired
    private LocalityService localityService;

    @Test
    void loadWithNotExistId() {
        var locality = new Locality();
        locality.setId(0L);
        assertThrows(NonExistEntity.class, ()->localityService.saveOrLoad(locality), "Locality with id " + locality.getId() + " not exist");
    }

    @Test
    void loadWithId() {
        var locality = new Locality();
        locality.setId(1L);

        var loaded = localityService.saveOrLoad(locality);
        assertEquals(locality.getId(), loaded.getId());
    }

    @Test
    void loadWithNameAndTypeRegionId() {
        var region = new Region()
                .setName("Тульская область")
                .setCode("71");
        region.setId(1L);

        var locality = new Locality()
                .setName("Замарино")
                .setType(Locality.Type.COUNTRY)
                .setRegion(region);


        var loaded = localityService.saveOrLoad(locality);
        assertEquals(locality, loaded);
        assertNotNull(loaded.getId());
        assertEquals(loaded.getId(), 2L);
    }

    @Test
    void loadWithNameAndTypeRegionNameAndRegionCode() {
        var region = new Region()
                .setName("Тульская область")
                .setCode("71");

        var locality = new Locality()
                .setName("Замарино")
                .setType(Locality.Type.COUNTRY)
                .setRegion(region);


        var loaded = localityService.saveOrLoad(locality);
        assertEquals(locality, loaded);
        assertNotNull(loaded.getId());
        assertEquals(loaded.getId(), 2L);
    }

    @Test
    void saveWithoutCountry() {
        var locality = new Locality()
                .setName("Неизвестный город")
                .setType(Locality.Type.COUNTRY);
        assertThrows(IllegalArgumentException.class, ()->localityService.saveOrLoad(locality), "Cannot save or load locality without region");
    }

    @Test
    void save_regionWithId() {
        var region = new Region();
        region.setId(2L);

        var locality = new Locality()
                .setName("Новый город")
                .setType(Locality.Type.CITY)
                .setRegion(region);


        var saved = localityService.saveOrLoad(locality);
        assertEquals(locality, saved);
        assertNotNull(saved.getId());

        localityService.delete(saved);
    }

    @Test
    void save_regionWithoutId() {
        var region = new Region()
                .setName("Нижегородская область")
                .setCode("52");

        var locality = new Locality()
                .setName("Новый город")
                .setType(Locality.Type.CITY)
                .setRegion(region);


        var saved = localityService.saveOrLoad(locality);
        assertEquals(locality, saved);
        assertNotNull(saved.getId());

        localityService.delete(saved);
    }
}