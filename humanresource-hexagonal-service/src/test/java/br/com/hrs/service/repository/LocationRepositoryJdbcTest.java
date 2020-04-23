package br.com.hrs.service.repository;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.service.DatabaseConfiguration;
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
@ContextConfiguration(classes = DatabaseConfiguration.class)
@ExtendWith(SpringExtension.class)
public class LocationRepositoryJdbcTest {

    public static final Integer LOCATION_ID = 3;
    public static final Integer NEW_LOCATION_ID = 24;

    Logger logger = LogManager.getLogger(LocationRepositoryJdbcTest.class);

    @Inject
    private Repository<Location, Integer> locationRepository;

    @Test
    @DisplayName("Finds for null Location")
    public void test01() {
        Location location = locationRepository.find(null);
        Assertions.assertNull(location, "Location should be null");
    }

    @Test
    @DisplayName("Finds for inexistent Location")
    public void test02() {
        Location location = locationRepository.find(-1);
        logger.info(location);
        Assertions.assertNull(location, "Location should be null");
    }

    @Test
    @DisplayName("Updates Location")
    public void test03() {
        Location location = locationRepository.find(LOCATION_ID);
        logger.info(location);
        location.setAddress(location.getAddress() + " altered");
        location.setState(location.getState() + " altered");
        location.setCity(location.getCity() + " altered");
        location.setPostalCode(location.getPostalCode() + "altered");

        boolean updated = locationRepository.update(location);

        Location locationSaved  = locationRepository.find(LOCATION_ID);

        Assertions.assertTrue(updated, "Location should be updated");
        Assertions.assertEquals(locationSaved.getAddress(), location.getAddress(), "Location address should be altered");
        Assertions.assertEquals(locationSaved.getState(), location.getState(), "Location state should be altered");
        Assertions.assertEquals(locationSaved.getCity(), location.getCity(), "Location city should be altered");
        Assertions.assertEquals(locationSaved.getPostalCode(), location.getPostalCode(), "Location postal code should be altered");
    }

    @Test
    @DisplayName("Saves Location")
    public void test04() {
        Location location = new Location("address", "postalCode", "city", "state", new Country.Builder().id("BR").build());
        Integer savedId = locationRepository.save(location);

        location = locationRepository.find(savedId);

        Assertions.assertNotNull(savedId, "Location should be saved and return its id");
        Assertions.assertNotNull(location, "Location should be saved");
        Assertions.assertTrue(location.getAddress().equals("address"), "Location address should be saved");
        Assertions.assertTrue(location.getPostalCode().equals("postalCode"), "Location postalCode should be saved");
        Assertions.assertTrue(location.getCity().equals("city"), "Location city should be saved");
        Assertions.assertTrue(location.getState().equals("state"), "Location state should be saved");
        Assertions.assertTrue(location.getCountry().getId().equals("BR"), "Location country should be saved");
        Assertions.assertTrue(savedId.equals(location.getId()), "Location saved needs to match saved id");
    }

    @Test
    @DisplayName("Finds all Locations")
    public void test05() {
        Collection<Location> locations = locationRepository.findAll();
        logger.info(locations);
        Assertions.assertNotNull(locations, "Locations should be listed");
        Assertions.assertTrue(locations.size() >= 4, "Locations should be listed at all");

    }

    @Test
    @DisplayName("Finds for Location")
    public void test06() {
        Location location = locationRepository.find(LOCATION_ID);
        logger.info(location);
        Assertions.assertNotNull(location, "Location should not be null");
    }

    @Test
    @DisplayName("Deletes Location")
    public void test07() {
        Location location = locationRepository.find(NEW_LOCATION_ID);
        logger.info(location);
        boolean deleted = locationRepository.delete(location.getId());
        boolean exists = locationRepository.exists(location.getId());
        Assertions.assertTrue(deleted, "Location should be deleted");
        Assertions.assertFalse(exists, "Location still existing");
    }

    @Test
    @DisplayName("Deletes inexistent Location")
    public void test08() {
        boolean deleted = locationRepository.delete(-1);
        Assertions.assertFalse(deleted, "Location should not be deleted");
    }

    @Test
    @DisplayName("Location exists")
    public void test09() {
        boolean exists = locationRepository.exists(LOCATION_ID);
        Assertions.assertTrue(exists, "Location should be existent");
    }

    @Test
    @DisplayName("Location doesn't exists")
    public void test10() {
        boolean exists = locationRepository.exists(-1);
        Assertions.assertFalse(exists, "Location should not be existent");
    }
}