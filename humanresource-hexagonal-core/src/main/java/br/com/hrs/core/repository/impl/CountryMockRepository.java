package br.com.hrs.core.repository.impl;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.Repository;

import javax.inject.Named;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Named
public class CountryMockRepository extends Repository<Country, String> {

    private Map<String, Country> database = new HashMap<>();

    public CountryMockRepository() {

        Country country01 = new Country.Builder()
                .id("IT")
                .name("Italy")
                .region(new Region.Builder().id(1).build())
            .build();

        Country country02 = new Country.Builder()
                .id("JP")
                .name("Japan")
                .region(new Region.Builder().id(3).build())
            .build();


        this.database.put(country01.getId(), country01);
        this.database.put(country02.getId(), country02);
    }

    @Override
    public Country find(String countryId) {
        logger.info("Fake database -> find({}})", countryId);
        return this.database.get(countryId);
    }

    @Override
    public String save(Country country) {
        logger.info("Fake database ->  save({}})", country);
        this.database.put(country.getId(), country);
        return country.getId();
    }

    @Override
    public boolean update(Country country) {
        logger.info("Fake database ->  update({}})", country);
        return this.database.put(country.getId(), country) != null;
    }

    @Override
    public Collection<Country> findAll() {
        logger.info("Fake database ->  Collection<Country> findAll()");
        return this.database.values();
    }

    @Override
    public boolean delete(String countryId) {
        logger.info("Fake database ->  delete({}})", countryId);
        return this.database.remove(countryId) != null;
    }

    @Override
    public boolean exists(String countryId) {
        logger.info("Fake database ->  exists({}})", countryId);
        return this.database.containsKey(countryId);
    }
}
