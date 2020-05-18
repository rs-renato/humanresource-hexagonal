package br.com.hrs.core.repository.impl;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.Repository;

public class LocationMockRepository extends Repository<Location, Integer> {

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
}
