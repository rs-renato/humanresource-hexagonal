package br.com.hrs.core.usecase.country;

import br.com.hrs.core.HrsBuildConfiguration;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Region;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;

@ContextConfiguration(classes = HrsBuildConfiguration.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Country Crud Use Case")
public class CountryCrudUseCaseTest {

    @Inject
    private CountryUseCase countryCrudUseCase;

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
        boolean exists = countryCrudUseCase.existsById(country.getId());
        Assertions.assertTrue(exists, String.format("Country should exist"));
    }

    @Test
    @DisplayName("Saves an Country")
    public void test02() {
        country.setId("NEW");
        countryCrudUseCase.save(country);
        Optional<Country> countryOpt = countryCrudUseCase.findById(country.getId());
        Assertions.assertEquals(country, countryOpt.get(), String.format("Country should be equals"));
    }

    @Test
    @DisplayName("Finds an Country")
    public void test03() {
        Optional<Country> countryOpt = countryCrudUseCase.findById(CountryCrudUseCaseTest.country.getId());
        Assertions.assertTrue(countryOpt.isPresent(), String.format("Country should not be null"));
    }

    @Test
    @DisplayName("Finds all Countrys")
    public void test04() {
        Collection<Country> countries = countryCrudUseCase.findAll();
        Assertions.assertNotNull(countries, String.format("Countrys should not be null"));
        Assertions.assertEquals(4, countries.size(), String.format("Countrys size doesn't match"));
    }

    @Test
    @DisplayName("Updates an Country")
    public void test05() {

        country.setName(country.getName() + "updated");

        countryCrudUseCase.update(country);

        Optional<Country> countryOpt = countryCrudUseCase.findById(country.getId());

        Assertions.assertTrue(countryOpt.isPresent(), String.format("Country should not be null"));
        Assertions.assertEquals(countryOpt.get().getName(), country.getName(), String.format("Country name should be updated"));
    }

    @Test
    @DisplayName("Deletes an Country")
    public void test06() {

        countryCrudUseCase.deleteById(country.getId());

        Optional<Country> countryOpt = countryCrudUseCase.findById(country.getId());

        Assertions.assertFalse(countryOpt.isPresent(), String.format("Country should be null"));
    }
}
