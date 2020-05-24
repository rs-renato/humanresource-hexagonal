package br.com.hrs.core.repository.mock;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.LocationRepository;

import javax.inject.Named;
import java.util.Arrays;
import java.util.Collection;

@Named
public class LocationMockRepository extends MockRepository<Location, Integer> implements LocationRepository {

    @Override
    public Collection<Location> buildCollection() {

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

        return Arrays.asList(location01, location02);
    }
}
