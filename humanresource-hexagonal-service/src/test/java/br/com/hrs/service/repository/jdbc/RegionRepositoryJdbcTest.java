package br.com.hrs.service.repository.jdbc;

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

@DisplayName("Repository - Region")
@ContextConfiguration(classes = HrsDatabaseConfiguration.class)
@ExtendWith(SpringExtension.class)
public class RegionRepositoryJdbcTest {

    public static final Integer REGION_ID = 3;
    public static final Integer NEW_REGION_ID = 5;

    Logger logger = LogManager.getLogger(RegionRepositoryJdbcTest.class);

    @Inject
    private Repository<Region, Integer> regionRepositoryJdbc;

    @Test
    @DisplayName("Finds for null Region")
    public void test01() {
        Region region = regionRepositoryJdbc.find(null);
        Assertions.assertNull(region, "Region should be null");
    }

    @Test
    @DisplayName("Finds for inexistent Region")
    public void test02() {
        Region region = regionRepositoryJdbc.find(-1);
        logger.info(region);
        Assertions.assertNull(region, "Region should be null");
    }

    @Test
    @DisplayName("Updates Region")
    public void test03() {
        Region region = regionRepositoryJdbc.find(REGION_ID);
        logger.info(region);
        region.setName(region.getName() + " altered");

        boolean updated = regionRepositoryJdbc.update(region);

        Region regionSaved  = regionRepositoryJdbc.find(REGION_ID);

        Assertions.assertTrue(updated, "Region should be updated");
        Assertions.assertEquals(regionSaved.getName(), region.getName(), "Region name should be altered");
    }

    @Test
    @DisplayName("Saves Region")
    public void test04() {
        Region region = new Region("new region");
        Integer savedId = regionRepositoryJdbc.save(region);

        region = regionRepositoryJdbc.find(savedId);

        Assertions.assertNotNull(savedId, "Region should be saved and return its id");
        Assertions.assertNotNull(region, "Region should be saved");
        Assertions.assertTrue(region.getName().contains("new"), "Region should be saved");
        Assertions.assertTrue(savedId.equals(region.getId()), "Region saved needs to match saved id");
    }

    @Test
    @DisplayName("Finds all Regions")
    public void test05() {
        Collection<Region> regions = regionRepositoryJdbc.findAll();
        logger.info(regions);
        Assertions.assertNotNull(regions, "Regions should be listed");
        Assertions.assertTrue(regions.size() >= 4, "Regions should be listed at all");

    }

    @Test
    @DisplayName("Finds for Region")
    public void test06() {
        Region region = regionRepositoryJdbc.find(REGION_ID);
        logger.info(region);
        Assertions.assertNotNull(region, "Region should not be null");
    }

    @Test
    @DisplayName("Deletes Region")
    public void test07() {
        Region region = regionRepositoryJdbc.find(NEW_REGION_ID);
        logger.info(region);
        boolean deleted = regionRepositoryJdbc.delete(region.getId());
        boolean exists = regionRepositoryJdbc.exists(region.getId());
        Assertions.assertTrue(deleted, "Region should be deleted");
        Assertions.assertFalse(exists, "Region still existing");
    }

    @Test
    @DisplayName("Deletes inexistent Region")
    public void test08() {
        boolean deleted = regionRepositoryJdbc.delete(-1);
        Assertions.assertFalse(deleted, "Region should not be deleted");
    }

    @Test
    @DisplayName("Region exists")
    public void test09() {
        boolean exists = regionRepositoryJdbc.exists(REGION_ID);
        Assertions.assertTrue(exists, "Region should be existent");
    }

    @Test
    @DisplayName("Region doesn't exists")
    public void test10() {
        boolean exists = regionRepositoryJdbc.exists(-1);
        Assertions.assertFalse(exists, "Region should not be existent");
    }
}