package br.com.hrs.core.usecase.country;

import br.com.hrs.core.HrsBuildConfiguration;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Region;
import br.com.hrs.core.usecase.CrudUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.util.Collection;

@ContextConfiguration(classes = HrsBuildConfiguration.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Country Crud Use Case")
public class CountryCrudUseCaseTest {

    @Inject
    private CrudUseCase<Country, String> countryCrudUseCase;

    private static Country country;

    @BeforeAll
    public static void setUp() {
        country = new Country.Builder()
                .id("IT")
                .name("Italy")
                .region(new Region.Builder().id(2).build())
                .build();
    }

    @Test
    @DisplayName("Exists an Countrys")
    public void test01() {
        boolean exists = countryCrudUseCase.exists(country.getId());
        Assertions.assertTrue(exists, String.format("Country should exist"));
    }

    @Test
    @DisplayName("Saves an Country")
    public void test02() {
        country.setId("NEW");
        countryCrudUseCase.save(country);
        Country countrySaved = countryCrudUseCase.find(country.getId());
        Assertions.assertEquals(country, countrySaved, String.format("Country should be equals"));
    }

    @Test
    @DisplayName("Finds an Country")
    public void test03() {
        Country countryFound = countryCrudUseCase.find(country.getId());
        Assertions.assertNotNull(countryFound, String.format("Country should not be null"));
    }

    @Test
    @DisplayName("Finds all Countrys")
    public void test04() {
        Collection<Country> countrys = countryCrudUseCase.findAll();
        Assertions.assertNotNull(countrys, String.format("Countrys should not be null"));
        Assertions.assertEquals(3, countrys.size(), String.format("Countrys size doesn't match"));
    }

    @Test
    @DisplayName("Updates an Country")
    public void test05() {

        country.setName(country.getName() + "updated");

        countryCrudUseCase.update(country);

        Country countryUpdated = countryCrudUseCase.find(country.getId());

        Assertions.assertNotNull(countryUpdated, String.format("Country should not be null"));
        Assertions.assertEquals(countryUpdated.getName(), country.getName(), String.format("Country name should be updated"));
    }

    @Test
    @DisplayName("Deletes an Country")
    public void test06() {

        boolean deleted = countryCrudUseCase.delete(country.getId());

        Country countryUpdated = countryCrudUseCase.find(country.getId());

        Assertions.assertTrue(deleted, String.format("Country should be deleted"));
        Assertions.assertNull(countryUpdated, String.format("Country should be null"));
    }
}
