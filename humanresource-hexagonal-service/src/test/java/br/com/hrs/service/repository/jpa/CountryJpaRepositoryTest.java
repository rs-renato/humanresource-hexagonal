package br.com.hrs.service.repository.jpa;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Region;
import br.com.hrs.service.repository.config.HrsJpaConfiguration;
import br.com.hrs.service.repository.jpa.impl.CountryJpaRepository;
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
import java.util.List;

@DisplayName("Jpa Repository - Country")
@ContextConfiguration(classes = HrsJpaConfiguration.class)
@ExtendWith(SpringExtension.class)
public class CountryJpaRepositoryTest {

    private static final String COUNTRY_ID = "IT";
    private static final Integer REGION_ID = 3;
    private static final String NEW_COUNTRY_ID = "NW";

    Logger logger = LogManager.getLogger(CountryJpaRepositoryTest.class);

    @Inject
//    private Repository<Country, String> countryJpaRepository;
    private CountryJpaRepository countryJpaRepository;

    @Test
    @DisplayName("Finds for inexistent Country")
    public void test01() {
        Country country = countryJpaRepository.find("NO_EXISTENT_COUNTRY");
        logger.info(country);
        Assertions.assertNull(country, "Country should be null");
    }

    @Test
    @DisplayName("Updates Country")
    public void test02() {
        Country country = countryJpaRepository.find(COUNTRY_ID);
        logger.info(country);
        country.setName(country.getName() + " altered");
        country.setRegion(new Region.Builder().id(REGION_ID).build());

        countryJpaRepository.update(country);

        Country countriesaved  = countryJpaRepository.find(COUNTRY_ID);

        Assertions.assertEquals(countriesaved.getName(), country.getName(), "Country name should be altered");
        Assertions.assertEquals(countriesaved.getRegion().getId(), country.getRegion().getId(), "Country region id should be altered");
    }

    @Test
    @DisplayName("Saves Country")
    public void test03() {

        Country country = new Country(NEW_COUNTRY_ID, "new country", new Region.Builder().id(REGION_ID).build());
        countryJpaRepository.save(country);

        country = countryJpaRepository.find(NEW_COUNTRY_ID);

        Assertions.assertNotNull(country, "Country should be saved");
        Assertions.assertEquals(NEW_COUNTRY_ID, country.getId(), "Country id should equals");
        Assertions.assertEquals(REGION_ID, country.getRegion().getId(), "Region id should equals");
    }

    @Test
    @DisplayName("Finds all countries")
    public void test04() {
        Collection<Country> countries = countryJpaRepository.findAll();
        logger.info(countries);
        Assertions.assertNotNull(countries, "countries should be listed");
        Assertions.assertTrue(countries.size() >= 25, "countries should be listed at all");

    }

    @Test
    @DisplayName("Finds for Country")
    public void test05() {
        Country country = countryJpaRepository.find(COUNTRY_ID);
        logger.info(country);
        Assertions.assertNotNull(country, "Country should not be null");
    }

    @Test
    @DisplayName("Finds for Region ID")
    public void test06() {
        List<Country> countries = countryJpaRepository.findByRegionId(REGION_ID);
        logger.info(countries);
        Assertions.assertNotNull(countries, "Country should not be null");
    }

    @Test
    @DisplayName("Deletes Country")
    public void test07() {
        Country country = countryJpaRepository.find(NEW_COUNTRY_ID);
        countryJpaRepository.delete(country.getId());
        boolean exists = countryJpaRepository.exists(country.getId());
        Assertions.assertFalse(exists, "Country still existing");
    }

    @Test
    @DisplayName("Country exists")
    public void test08() {
        boolean exists = countryJpaRepository.exists(COUNTRY_ID);
        Assertions.assertTrue(exists, "Country should be existent");
    }

    @Test
    @DisplayName("Country doesn't exists")
    public void test09() {
        boolean exists = countryJpaRepository.exists("NO_EXISTENT_COUNTRY");
        Assertions.assertFalse(exists, "Country should not be existent");
    }
}