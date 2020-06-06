package br.com.hrs.persistence.repository;

import br.com.hrs.core.exception.HrsMandatoryException;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.CountryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class CountryRepositoryTest {

    private static final String COUNTRY_ID = "IT";
    private static final Integer REGION_ID = 3;
    private static final String NEW_COUNTRY_ID = "NW";

//    protected abstract Repository<Country, String> getCountryRepository();

    protected abstract CountryRepository getCountryRepository();
    
    @Test
    @DisplayName("Finds for null Country")
    public void test01() {
        RuntimeException exception = Assertions.assertThrows(HrsMandatoryException.class, () -> {
            getCountryRepository().findById(null);
        });

        Assertions.assertTrue(exception.getMessage().matches("Country.*mandatory"), String.format("Country ID mandatory message wrong (%s)", exception.getMessage()));
    }

    @Test
    @DisplayName("Finds for inexistent Country")
    public void test02() {
        Optional<Country> countryOpt = getCountryRepository().findById("NO_EXISTENT_COUNTRY");
        Assertions.assertFalse(countryOpt.isPresent(), "Country should be null");
    }

    @Test
    @DisplayName("Updates Country")
    public void test03() {
        Optional<Country> countryOpt = getCountryRepository().findById(COUNTRY_ID);

        countryOpt.get().setName(countryOpt.get().getName() + " altered");
        countryOpt.get().setRegion(new Region.Builder().id(REGION_ID).build());

        getCountryRepository().update(countryOpt.get());

        Optional<Country> countryOptSaved  = getCountryRepository().findById(COUNTRY_ID);

        Assertions.assertEquals(countryOptSaved.get().getName(), countryOpt.get().getName(), "Country name should be altered");
        Assertions.assertEquals(countryOptSaved.get().getRegion().getId(), countryOpt.get().getRegion().getId(), "Country region id should be altered");
    }

    @Test
    @DisplayName("Saves Country")
    public void test04() {
        Country country = new Country(NEW_COUNTRY_ID, "new country", new Region.Builder().id(REGION_ID).build());
        getCountryRepository().save(country);

        Optional<Country> countryOpt = getCountryRepository().findById(NEW_COUNTRY_ID);

        Assertions.assertTrue(countryOpt.isPresent(), "Country should be saved");
        Assertions.assertTrue(countryOpt.get().getName().contains("new"), "Country should be saved");
    }

    @Test
    @DisplayName("Finds all Countries")
    public void test05() {
        Collection<Country> countries = getCountryRepository().findAll();
        Assertions.assertNotNull(countries, "Countries should be listed");
        Assertions.assertTrue(countries.size() >= 25, "Countries should be listed at all");
    }

    @Test
    @DisplayName("Finds for Country")
    public void test06() {
        Optional<Country> countryOpt = getCountryRepository().findById(COUNTRY_ID);
        Assertions.assertTrue(countryOpt.isPresent(), "Country should not be null");
    }

    @Test
    @DisplayName("Deletes Country")
    public void test07() {
        Optional<Country> country = getCountryRepository().findById(NEW_COUNTRY_ID);
        getCountryRepository().deleteById(country.get().getId());
        boolean exists = getCountryRepository().existsById(country.get().getId());
        Assertions.assertFalse(exists, "Country still existing");
    }

    @Test
    @DisplayName("Country exists")
    public void test08() {
        boolean exists = getCountryRepository().existsById(COUNTRY_ID);
        Assertions.assertTrue(exists, "Country should be existent");
    }

    @Test
    @DisplayName("Country doesn't exists")
    public void test09() {
        boolean exists = getCountryRepository().existsById("NO_EXISTENT_COUNTRY");
        Assertions.assertFalse(exists, "Country should not be existent");
    }

    @Test
    @DisplayName("Finds Countries by Region ID")
    public void test10(){
        Optional<List<Country>> countriesOpt = getCountryRepository().findByRegionId(REGION_ID);
        Assertions.assertTrue(countriesOpt.isPresent(), "Countries should be listed");
        Assertions.assertTrue(countriesOpt.get().size() == 7, "Countries should be listed at all");
    }
}