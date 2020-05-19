package br.com.hrs.service.repository.jdbc;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.service.HrsDatabaseConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.util.Collection;

@DisplayName("Repository - Location")
@ContextConfiguration(classes = HrsDatabaseConfiguration.class)
@ExtendWith(SpringExtension.class)
public class LocationRepositoryJdbcTest {

    public static final Integer LOCATION_ID = 3;
    public static final Integer NEW_LOCATION_ID = 24;

    Logger logger = LogManager.getLogger(LocationRepositoryJdbcTest.class);

    @Inject
    private Repository<Location, Integer> locationRepositoryJdbc;

    @Test
    @DisplayName("Finds for null Location")
    public void test01() {
        Location location = locationRepositoryJdbc.find(null);
        Assertions.assertNull(location, "Location should be null");
    }

    @Test
    @DisplayName("Finds for inexistent Location")
    public void test02() {
        Location location = locationRepositoryJdbc.find(-1);
        logger.info(location);
        Assertions.assertNull(location, "Location should be null");
    }

    @Test
    @DisplayName("Updates Location")
    public void test03() {
        Location location = locationRepositoryJdbc.find(LOCATION_ID);
        logger.info(location);
        location.setAddress(location.getAddress() + " altered");
        location.setState(location.getState() + " altered");
        location.setCity(location.getCity() + " altered");
        location.setPostalCode(location.getPostalCode() + "altered");

        locationRepositoryJdbc.update(location);

        Location locationSaved  = locationRepositoryJdbc.find(LOCATION_ID);

        Assertions.assertEquals(locationSaved.getAddress(), location.getAddress(), "Location address should be altered");
        Assertions.assertEquals(locationSaved.getState(), location.getState(), "Location state should be altered");
        Assertions.assertEquals(locationSaved.getCity(), location.getCity(), "Location city should be altered");
        Assertions.assertEquals(locationSaved.getPostalCode(), location.getPostalCode(), "Location postal code should be altered");
    }

    @Test
    @DisplayName("Saves Location")
    public void test04() {
        Location location = new Location("address", "postalCode", "city", "state", new Country.Builder().id("BR").build());
        Location locationSaved= locationRepositoryJdbc.save(location);
        Assertions.assertNotNull(locationSaved, "Location saved should not be null");
        Location locationFound = locationRepositoryJdbc.find(locationSaved.getId());
        Assertions.assertEquals(locationSaved, locationFound, String.format("Location should be equals"));
    }

    @Test
    @DisplayName("Finds all Locations")
    public void test05() {
        Collection<Location> locations = locationRepositoryJdbc.findAll();
        logger.info(locations);
        Assertions.assertNotNull(locations, "Locations should be listed");
        Assertions.assertTrue(locations.size() >= 4, "Locations should be listed at all");

    }

    @Test
    @DisplayName("Finds for Location")
    public void test06() {
        Location location = locationRepositoryJdbc.find(LOCATION_ID);
        logger.info(location);
        Assertions.assertNotNull(location, "Location should not be null");
    }

    @Test
    @DisplayName("Deletes Location")
    public void test07() {
        Location location = locationRepositoryJdbc.find(NEW_LOCATION_ID);
        logger.info(location);
        boolean deleted = locationRepositoryJdbc.delete(location.getId());
        boolean exists = locationRepositoryJdbc.exists(location.getId());
        Assertions.assertTrue(deleted, "Location should be deleted");
        Assertions.assertFalse(exists, "Location still existing");
    }

    @Test
    @DisplayName("Deletes inexistent Location")
    public void test08() {
        boolean deleted = locationRepositoryJdbc.delete(-1);
        Assertions.assertFalse(deleted, "Location should not be deleted");
    }

    @Test
    @DisplayName("Location exists")
    public void test09() {
        boolean exists = locationRepositoryJdbc.exists(LOCATION_ID);
        Assertions.assertTrue(exists, "Location should be existent");
    }

    @Test
    @DisplayName("Location doesn't exists")
    public void test10() {
        boolean exists = locationRepositoryJdbc.exists(-1);
        Assertions.assertFalse(exists, "Location should not be existent");
    }
}