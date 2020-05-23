package br.com.hrs.service.repository.jdbc;

import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.service.repository.config.HrsJdbcConfiguration;
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

@DisplayName("Jdbc Repository - Region")
@ContextConfiguration(classes = HrsJdbcConfiguration.class)
@ExtendWith(SpringExtension.class)
public class RegionJdbcRepositoryTest {

    private static final Integer REGION_ID = 3;
    private static final Integer NEW_REGION_ID = 5;

    Logger logger = LogManager.getLogger(RegionJdbcRepositoryTest.class);

    @Inject
    private Repository<Region, Integer> regionJdbcRepository;

    @Test
    @DisplayName("Finds for null Region")
    public void test01() {
        Region region = regionJdbcRepository.find(null);
        Assertions.assertNull(region, "Region should be null");
    }

    @Test
    @DisplayName("Finds for inexistent Region")
    public void test02() {
        Region region = regionJdbcRepository.find(-1);
        logger.info(region);
        Assertions.assertNull(region, "Region should be null");
    }

    @Test
    @DisplayName("Updates Region")
    public void test03() {
        Region region = regionJdbcRepository.find(REGION_ID);
        logger.info(region);
        region.setName(region.getName() + " altered");

        regionJdbcRepository.update(region);

        Region regionSaved  = regionJdbcRepository.find(REGION_ID);

        
        Assertions.assertEquals(regionSaved.getName(), region.getName(), "Region name should be altered");
    }

    @Test
    @DisplayName("Saves Region")
    public void test04() {
        Region region = new Region("new region");
        Region regionSaved= regionJdbcRepository.save(region);
        Assertions.assertNotNull(regionSaved, "Region saved should not be null");
        Region regionFound = regionJdbcRepository.find(regionSaved.getId());
        Assertions.assertEquals(regionSaved, regionFound,"Region should be equals");
    }

    @Test
    @DisplayName("Finds all Regions")
    public void test05() {
        Collection<Region> regions = regionJdbcRepository.findAll();
        logger.info(regions);
        Assertions.assertNotNull(regions, "Regions should be listed");
        Assertions.assertTrue(regions.size() >= 4, "Regions should be listed at all");

    }

    @Test
    @DisplayName("Finds for Region")
    public void test06() {
        Region region = regionJdbcRepository.find(REGION_ID);
        logger.info(region);
        Assertions.assertNotNull(region, "Region should not be null");
    }

    @Test
    @DisplayName("Deletes Region")
    public void test07() {
        Region region = regionJdbcRepository.find(NEW_REGION_ID);
        logger.info(region);
        regionJdbcRepository.delete(region.getId());
        boolean exists = regionJdbcRepository.exists(region.getId());
        Assertions.assertFalse(exists, "Region still existing");
    }

    @Test
    @DisplayName("Region exists")
    public void test08() {
        boolean exists = regionJdbcRepository.exists(REGION_ID);
        Assertions.assertTrue(exists, "Region should be existent");
    }

    @Test
    @DisplayName("Region doesn't exists")
    public void test09() {
        boolean exists = regionJdbcRepository.exists(-1);
        Assertions.assertFalse(exists, "Region should not be existent");
    }
}