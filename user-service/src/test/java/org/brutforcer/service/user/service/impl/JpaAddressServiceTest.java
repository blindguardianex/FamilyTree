package org.brutforcer.service.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.brutforcer.service.user.entity.Address;
import org.brutforcer.service.user.entity.Country;
import org.brutforcer.service.user.entity.Locality;
import org.brutforcer.service.user.entity.Region;
import org.brutforcer.service.user.service.AddressService;
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
class JpaAddressServiceTest {

    @Autowired
    private AddressService addressService;

    @Test
    void getById() {
        var byId = addressService.getById(1L);
        assertTrue(byId.isPresent());

        byId = addressService.getById(0L);
        assertTrue(byId.isEmpty());
    }

    @Test
    void getByTextAddress() {
        var byTextAddress = addressService.getByTextAddress("Российская Федерация, Нижегородская область, Дзержинск город");
        assertTrue(byTextAddress.isPresent());

        byTextAddress = addressService.getByTextAddress("Unknown address");
        assertTrue(byTextAddress.isEmpty());
    }

    @Test
    void registry_successfully() {
        var country = new Country();
        country.setId(3L);
        var region = new Region()
                .setName("Какой то новый регион")
                .setCode("UNK")
                .setCountry(country);
        var locality = new Locality()
                .setType(Locality.Type.CITY)
                .setRegion(region)
                .setName("Городок");
        Address address = new Address()
                .setLocality(locality)
                .setRegion(region)
                .setCountry(country);

        var registry = addressService.registry(address);
        System.out.println(registry);
    }
}