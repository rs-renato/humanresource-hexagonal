package br.com.hrs.core.usecase.country;

import br.com.hrs.core.HrsBuildConfiguration;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.pagination.Pagination;
import br.com.hrs.core.usecase.filter.CountriesFromAmericaOrEuropeStreamingFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.util.Collection;
import java.util.LinkedList;
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
    @DisplayName("Exists an Countries")
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
    @DisplayName("Finds all Countries")
    public void test04() {
        Collection<Country> countries = countryCrudUseCase.findAll();
        Assertions.assertNotNull(countries, String.format("Countries should not be null"));
        Assertions.assertEquals(4, countries.size(), String.format("Countries size doesn't match"));
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

    @Test
    @DisplayName("Finds all Countries Paginated")
    public void test07() {

        long count = countryCrudUseCase.count();

        int pageSize = 1;

        for (int i = 1; i < count; i++) {

            int page = i * pageSize;

            Pagination pagination = new  Pagination(page, pageSize);

            Collection<Country> countries = countryCrudUseCase.findAll(pagination);
            Assertions.assertNotNull(countries, "Countries should be listed");
            Assertions.assertTrue(countries.size() <= pageSize, "Countries should be listed at all");

            new LinkedList<Country>(countryCrudUseCase.findAll())
                    .subList(page, page + pageSize)
                    .forEach(c -> c.equals(countries.iterator().next()));
        }
    }

    @Test
    @DisplayName("Finds all Countries by Filter")
    public void test08() {

        Collection<Country> countries =  countryCrudUseCase.findAll(new CountriesFromAmericaOrEuropeStreamingFilter());
        Assertions.assertNotNull(countries, "Countries should be listed");
        Assertions.assertEquals(countries.size(),2, "Countries should be listed at all");
    }
}