package br.com.hrs.core.repository.impl;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.LocationRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LocationMockRepository implements LocationRepository {

    private Map<Integer, Location> database = new HashMap<>();

    public LocationMockRepository() {

        Location location01 = new Location.Builder()
                                .id(1400)
                                .address("2014 Jabberwocky Rd")
                                .postalCode("26192")
                                .city("Southlake")
                                .state("Texas")
                                .country(new Country.Builder().id("US").build())
                            .build();

        Location location02 = new Location.Builder()
                                .id(1500)
                                .address("2011 Interiors Blvd")
                                .postalCode("99236")
                                .city("South San Francisco")
                                .state("California")
                                .country(new Country.Builder().id("US").build())
                            .build();

        database.put(location01.getId(), location01);
        database.put(location02.getId(), location02);
    }

    @Override
    public Location find(Integer locationId) {
        logger.info("Fake database ->  Location get({}})", locationId);
        return this.database.get(locationId);
    }

    @Override
    public Integer save(Location location) {
        logger.info("Fake database ->  save({}})", location);
        this.database.put(location.getId(), location);
        return location.getId();
    }

    @Override
    public boolean update(Location location) {
        logger.info("Fake database ->  update({}})", location);
        return this.database.put(location.getId(), location) != null;
    }

    @Override
    public Collection<Location> findAll() {
        logger.info("Fake database ->  Collection<Location> list()");
        return this.database.values();
    }

    @Override
    public boolean delete(Integer locationId) {
        logger.info("Fake database ->  delete({}})", locationId);
        return this.database.remove(locationId) != null;
    }
}
