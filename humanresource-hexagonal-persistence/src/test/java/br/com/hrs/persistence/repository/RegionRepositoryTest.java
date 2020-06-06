package br.com.hrs.persistence.repository;

import br.com.hrs.core.exception.HrsMandatoryException;
import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.RegionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Optional;

public abstract class RegionRepositoryTest {

    private static final Integer REGION_ID = 3;
    private static Integer NEW_REGION_ID;

    Logger logger = LogManager.getLogger(RegionRepositoryTest.class);

    protected abstract RegionRepository getRegionRepository();
    
    @Test
    @DisplayName("Finds for null Region")
    public void test01() {
        RuntimeException exception = Assertions.assertThrows(HrsMandatoryException.class, () -> {
            getRegionRepository().findById(null);
        });

        Assertions.assertTrue(exception.getMessage().matches("Region.*mandatory"), String.format("Region ID mandatory message wrong (%s)", exception.getMessage()));
    }

    @Test
    @DisplayName("Finds for inexistent Region")
    public void test02() {
        Optional<Region> regionOpt = getRegionRepository().findById(-1);
        Assertions.assertFalse(regionOpt.isPresent(), "Region should be null");
    }

    @Test
    @DisplayName("Updates Region")
    public void test03() {
        Optional<Region> regionOpt = getRegionRepository().findById(REGION_ID);

        regionOpt.get().setName(regionOpt.get().getName() + " altered");

        getRegionRepository().update(regionOpt.get());

        Optional<Region> regionOptSaved  = getRegionRepository().findById(REGION_ID);

        Assertions.assertEquals(regionOptSaved.get().getName(), regionOpt.get().getName(), "Region name should be altered");
        Assertions.assertEquals(regionOptSaved.get().getId(), regionOpt.get().getId(), "Region id should be altered");
    }

    @Test
    @DisplayName("Saves Region")
    public void test04() {
        Region region = new Region("new region");
        Region regionSaved =  getRegionRepository().save(region);

        Optional<Region> regionOpt = getRegionRepository().findById(regionSaved.getId());

        Assertions.assertTrue(regionOpt.isPresent(), "Region should be saved");
        Assertions.assertEquals(regionOpt.get().getName(), region.getName(), "Region should be saved");

        NEW_REGION_ID = regionSaved.getId();
    }

    @Test
    @DisplayName("Finds all Regions")
    public void test05() {
        Collection<Region> regions = getRegionRepository().findAll();
        logger.info(regions);
        Assertions.assertNotNull(regions, "Regions should be listed");
        Assertions.assertEquals(5, regions.size(), "Regions should be listed at all");
    }

    @Test
    @DisplayName("Finds for Region")
    public void test06() {
        Optional<Region> regionOpt = getRegionRepository().findById(REGION_ID);
        Assertions.assertTrue(regionOpt.isPresent(), "Region should not be null");
    }

    @Test
    @DisplayName("Deletes Region")
    public void test07() {
        getRegionRepository().deleteById(NEW_REGION_ID);
        boolean exists = getRegionRepository().existsById(NEW_REGION_ID);
        Assertions.assertFalse(exists, "Region still existing");
    }

    @Test
    @DisplayName("Region exists")
    public void test08() {
        boolean exists = getRegionRepository().existsById(REGION_ID);
        Assertions.assertTrue(exists, "Region should be existent");
    }

    @Test
    @DisplayName("Region doesn't exists")
    public void test09() {
        boolean exists = getRegionRepository().existsById(-1);
        Assertions.assertFalse(exists, "Region should not be existent");
    }
}