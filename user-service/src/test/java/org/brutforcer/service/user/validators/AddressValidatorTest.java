package org.brutforcer.service.user.validators;

import org.brutforcer.service.user.entity.Address;
import org.brutforcer.service.user.entity.Country;
import org.brutforcer.service.user.entity.Locality;
import org.brutforcer.service.user.entity.Region;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressValidatorTest {

    private final AddressValidator validator = new AddressValidator();

    @Test
    void successfullyValidate() {
        var country = new Country().setName("Россия").setCode("RUS");
        var region = new Region().setName("Тульская область").setCode("71").setCountry(country);
        var locality = new Locality().setName("Тула").setType(Locality.Type.CITY).setRegion(region);
        Address address = new Address()
                .setCountry(country)
                .setRegion(region)
                .setLocality(locality);
        assertDoesNotThrow(()->validator.validate(address));
    }

    @Test
    void nullCountry() {
        Address address = new Address()
                .setRegion(new Region())
                .setLocality(new Locality());
        assertThrows(IllegalArgumentException.class, ()->validator.validate(address),"Address country is null");
    }

    @Test
    void nullRegion() {
        Address address = new Address()
                .setCountry(new Country())
                .setLocality(new Locality());
        assertThrows(IllegalArgumentException.class, ()->validator.validate(address),"Address region is null");
    }

    @Test
    void nullLocality() {
        Address address = new Address()
                .setCountry(new Country())
                .setRegion(new Region());
        assertThrows(IllegalArgumentException.class, ()->validator.validate(address),"Address locality is null");
    }

    @Test
    void nullCountryInRegion() {
        Address address = new Address()
                .setCountry(new Country())
                .setRegion(new Region())
                .setLocality(new Locality());
        assertThrows(IllegalArgumentException.class, ()->validator.validate(address),"Region has null country");
    }

    @Test
    void nullRegionInLocality() {
        Address address = new Address()
                .setCountry(new Country())
                .setRegion(new Region().setCountry(new Country()))
                .setLocality(new Locality());
        assertThrows(IllegalArgumentException.class, ()->validator.validate(address),"Locality has null region");
    }

    @Test
    void notConsistencyCountry() {
        Address address = new Address()
                .setCountry(new Country().setName("Россия").setCode("RUS"))
                .setRegion(new Region().setCountry(new Country().setName("Германия").setCode("DEU")))
                .setLocality(new Locality());
        assertThrows(IllegalArgumentException.class, ()->validator.validate(address),"Address country not equals region country");
    }

    @Test
    void notConsistencyRegion() {
        var country = new Country().setName("Россия").setCode("RUS");
        Address address = new Address()
                .setCountry(country)
                .setRegion(new Region().setName("Тульская область").setCode("71").setCountry(country))
                .setLocality(new Locality().setRegion(new Region().setName("Ростовская область область").setCode("88")));
        assertThrows(IllegalArgumentException.class, ()->validator.validate(address),"Address country not equals region country");
    }
}