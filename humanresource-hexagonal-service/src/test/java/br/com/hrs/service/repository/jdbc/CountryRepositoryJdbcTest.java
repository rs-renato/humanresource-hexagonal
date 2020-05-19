package br.com.hrs.service.repository.jdbc;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Region;
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

@DisplayName("Repository - Country")
@ContextConfiguration(classes = HrsDatabaseConfiguration.class)
@ExtendWith(SpringExtension.class)
public class CountryRepositoryJdbcTest {

    public static final String COUNTRY_ID = "IT";
    public static final Integer REGION_ID = 3;
    public static final String NEW_COUNTRY_ID = "NW";

    Logger logger = LogManager.getLogger(CountryRepositoryJdbcTest.class);

    @Inject
    private Repository<Country, String> contryRepositoryJdbc;

    @Test
    @DisplayName("Finds for null Country")
    public void test01() {
        Country country = contryRepositoryJdbc.find(null);
        Assertions.assertNull(country, "Country should be null");
    }

    @Test
    @DisplayName("Finds for inexistent Country")
    public void test02() {
        Country country = contryRepositoryJdbc.find("NO_EXISTENT_COUNTRY");
        logger.info(country);
        Assertions.assertNull(country, "Country should be null");
    }

    @Test
    @DisplayName("Updates Country")
    public void test03() {
        Country country = contryRepositoryJdbc.find(COUNTRY_ID);
        logger.info(country);
        country.setName(country.getName() + " altered");
        country.setRegion(new Region.Builder().id(REGION_ID).build());

        contryRepositoryJdbc.update(country);

        Country countrySaved  = contryRepositoryJdbc.find(COUNTRY_ID);

        Assertions.assertEquals(countrySaved.getName(), country.getName(), "Country name should be altered");
        Assertions.assertEquals(countrySaved.getRegion().getId(), country.getRegion().getId(), "Country region id should be altered");
    }

    @Test
    @DisplayName("Saves Country")
    public void test04() {
        Country country = new Country(NEW_COUNTRY_ID, "new country", new Region.Builder().id(REGION_ID).build());
        contryRepositoryJdbc.save(country);

        country = contryRepositoryJdbc.find(NEW_COUNTRY_ID);

        Assertions.assertNotNull(country, "Country should be saved");
        Assertions.assertTrue(country.getName().contains("new"), "Country should be saved");
    }

    @Test
    @DisplayName("Finds all Countrys")
    public void test05() {
        Collection<Country> countrys = contryRepositoryJdbc.findAll();
        logger.info(countrys);
        Assertions.assertNotNull(countrys, "Countrys should be listed");
        Assertions.assertTrue(countrys.size() >= 25, "Countrys should be listed at all");

    }

    @Test
    @DisplayName("Finds for Country")
    public void test06() {
        Country country = contryRepositoryJdbc.find(COUNTRY_ID);
        logger.info(country);
        Assertions.assertNotNull(country, "Country should not be null");
    }

    @Test
    @DisplayName("Deletes Country")
    public void test07() {
        Country country = contryRepositoryJdbc.find(NEW_COUNTRY_ID);
        logger.info(country);
        boolean deleted = contryRepositoryJdbc.delete(country.getId());
        boolean exists = contryRepositoryJdbc.exists(country.getId());
        Assertions.assertTrue(deleted, "Country should be deleted");
        Assertions.assertFalse(exists, "Country still existing");
    }

    @Test
    @DisplayName("Deletes inexistent Country")
    public void test08() {
        boolean deleted = contryRepositoryJdbc.delete("INEXISTEND_COUNTRY");
        Assertions.assertFalse(deleted, "Country should not be deleted");
    }

    @Test
    @DisplayName("Country exists")
    public void test09() {
        boolean exists = contryRepositoryJdbc.exists(COUNTRY_ID);
        Assertions.assertTrue(exists, "Country should be existent");
    }

    @Test
    @DisplayName("Country doesn't exists")
    public void test10() {
        boolean exists = contryRepositoryJdbc.exists("NO_EXISTENT_COUNTRY");
        Assertions.assertFalse(exists, "Country should not be existent");
    }
}