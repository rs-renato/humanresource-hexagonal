package br.com.hrs.persistence.repository;

import br.com.hrs.core.exception.HrsMandatoryException;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.LocationRepository;
import br.com.hrs.core.repository.pagination.Pagination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public abstract class LocationRepositoryTest {

    private static final Integer LOCATION_ID = 1;
    private static final String COUNTRY_ID = "IT";
    private static Integer NEW_LOCATION_ID;

    Logger logger = LogManager.getLogger(LocationRepositoryTest.class);

    protected abstract LocationRepository getLocationRepository();

    @Test
    @DisplayName("Finds for null Location")
    public void test01() {
        RuntimeException exception = Assertions.assertThrows(HrsMandatoryException.class, () -> {
            getLocationRepository().findById(null);
        });

        Assertions.assertTrue(exception.getMessage().matches("Location.*mandatory"), String.format("Location ID mandatory message wrong (%s)", exception.getMessage()));
    }

    @Test
    @DisplayName("Finds for inexistent Location")
    public void test02() {
        Optional<Location> locationOpt = getLocationRepository().findById(-1);
        Assertions.assertFalse(locationOpt.isPresent(), "Location should be null");
    }

    @Test
    @DisplayName("Updates Location")
    public void test03() {
        Optional<Location> locationOpt = getLocationRepository().findById(LOCATION_ID);

        locationOpt.get().setState(locationOpt.get().getState() + " altered");
        locationOpt.get().setCountry(new Country.Builder().id(COUNTRY_ID).build());

        getLocationRepository().update(locationOpt.get());

        Optional<Location> locationOptSaved  = getLocationRepository().findById(LOCATION_ID);

        Assertions.assertEquals(locationOptSaved.get().getState(), locationOpt.get().getState(), "Location state should be altered");
        Assertions.assertEquals(locationOptSaved.get().getCountry().getId(), locationOpt.get().getCountry().getId(), "Location country id should be altered");
    }

    @Test
    @DisplayName("Saves Location")
    public void test04() {
        Location location = new Location.Builder()
                .address("some address")
                .postalCode("123456")
                .city("goiania")
                .state("goias")
                .country(new Country.Builder().id("BR").build())
                .build();

        Location locationSaved = getLocationRepository().save(location);

        Optional<Location> locationOpt = getLocationRepository().findById(locationSaved.getId());

        Assertions.assertTrue(locationOpt.isPresent(), "Location should be saved");
        Assertions.assertEquals(location.getAddress(), locationOpt.get().getAddress(), "Location address should match");
        Assertions.assertEquals(location.getCountry().getId(), locationOpt.get().getCountry().getId(), "Location country should match");

        NEW_LOCATION_ID = locationSaved.getId();
    }

    @Test
    @DisplayName("Finds all Locations")
    public void test05() {
        Collection<Location> locations = getLocationRepository().findAll();
        logger.info(locations);
        Assertions.assertNotNull(locations, "Locations should be listed");
        Assertions.assertEquals(24, locations.size(), "Locations should be listed at all");
    }

    @Test
    @DisplayName("Finds for Location")
    public void test06() {
        Optional<Location> locationOpt = getLocationRepository().findById(LOCATION_ID);
        Assertions.assertTrue(locationOpt.isPresent(), "Location should not be null");
    }

    @Test
    @DisplayName("Deletes Location")
    public void test07() {
        Optional<Location> location = getLocationRepository().findById(NEW_LOCATION_ID);
        getLocationRepository().deleteById(location.get().getId());
        boolean exists = getLocationRepository().existsById(location.get().getId());
        Assertions.assertFalse(exists, "Location still existing");
    }

    @Test
    @DisplayName("Location exists")
    public void test08() {
        boolean exists = getLocationRepository().existsById(LOCATION_ID);
        Assertions.assertTrue(exists, "Location should be existent");
    }

    @Test
    @DisplayName("Location doesn't exists")
    public void test09() {
        boolean exists = getLocationRepository().existsById(-1);
        Assertions.assertFalse(exists, "Location should not be existent");
    }

    @Test
    @DisplayName("Finds all Locations Paginated")
    public void test10() {

        long count = getLocationRepository().count();

        int pageSize = 1;

        for (int i = 1; i < count; i++) {

            int page = i * pageSize;

            Pagination pagination = new  Pagination(page, pageSize);

            Collection<Location> locations = getLocationRepository().findAll(pagination);
            Assertions.assertNotNull(locations, "Locations should be listed");
            Assertions.assertTrue(locations.size() <= pageSize, "Locations should be listed at all");

            new LinkedList<Location>(getLocationRepository().findAll())
                    .subList(page, page + pageSize)
                    .forEach(c -> c.equals(locations.iterator().next()));
        }
    }
}