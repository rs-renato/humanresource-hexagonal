package br.com.hrs.core.usecase.location;

import br.com.hrs.core.HrsBuildConfiguration;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Location;
import br.com.hrs.core.usecase.CrudUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.text.ParseException;
import java.util.Collection;

@ContextConfiguration(classes = HrsBuildConfiguration.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Location Crud Use Case")
public class LocationCrudUseCaseTest {

    @Inject
    private CrudUseCase<Location, Integer> locationCrudUseCase;

    private static Location location;

    @BeforeAll
    public static void setUp() throws ParseException {
        location = new Location.Builder()
                .id(1400)
                .address("2014 Jabberwocky Rd")
                .postalCode("26192")
                .city("Southlake")
                .state("Texas")
                .country(new Country.Builder().id("US").build())
                .build();
    }

    @Test
    @DisplayName("Exists an Locations")
    public void test01() {
        boolean exists = locationCrudUseCase.exists(location.getId());
        Assertions.assertTrue(exists, String.format("Location should exist"));
    }

    @Test
    @DisplayName("Saves an Location")
    public void test02() {
        Location locationSaved = locationCrudUseCase.save(location);
        Assertions.assertNotNull(locationSaved, String.format("Location saved should not be null"));
        Location locationFound = locationCrudUseCase.find(locationSaved.getId());
        Assertions.assertEquals(locationSaved, locationFound, String.format("Location should be equals"));
    }

    @Test
    @DisplayName("Finds an Location")
    public void test03() {
        Location locationFound = locationCrudUseCase.find(location.getId());
        Assertions.assertNotNull(locationFound, String.format("Location should not be null"));
    }

    @Test
    @DisplayName("Finds all Locations")
    public void test04() {
        Collection<Location> locations = locationCrudUseCase.findAll();
        Assertions.assertNotNull(locations, String.format("Locations should not be null"));
        Assertions.assertEquals(2, locations.size(), String.format("Locations size doesn't match"));
    }

    @Test
    @DisplayName("Updates an Location")
    public void test05() {

        location.setState(location.getState() + "updated");

        locationCrudUseCase.update(location);

        Location locationUpdated = locationCrudUseCase.find(location.getId());

        Assertions.assertNotNull(locationUpdated, String.format("Location should not be null"));
        Assertions.assertEquals(locationUpdated.getState(), location.getState(), String.format("Location state should be updated"));
    }

    @Test
    @DisplayName("Deletes an Location")
    public void test06() {

        boolean deleted = locationCrudUseCase.delete(location.getId());

        Location locationUpdated = locationCrudUseCase.find(location.getId());

        Assertions.assertTrue(deleted, String.format("Location should be deleted"));
        Assertions.assertNull(locationUpdated, String.format("Location should be null"));
    }
}
