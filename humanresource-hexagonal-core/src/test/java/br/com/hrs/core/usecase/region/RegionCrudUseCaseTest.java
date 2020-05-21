package br.com.hrs.core.usecase.region;

import br.com.hrs.core.HrsBuildConfiguration;
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
@DisplayName("Region Crud Use Case")
public class RegionCrudUseCaseTest {

    @Inject
    private CrudUseCase<Region, Integer> regionCrudUseCase;

    private static Region region;

    @BeforeAll
    public static void setUp() {
        region = new Region.Builder()
                .id(1)
                .name("Europe")
                .build();
    }

    @Test
    @DisplayName("Exists an Regions")
    public void test01() {
        boolean exists = regionCrudUseCase.exists(region.getId());
        Assertions.assertTrue(exists, String.format("Region should exist"));
    }

    @Test
    @DisplayName("Saves an Region")
    public void test02() {
        Region regionSaved = regionCrudUseCase.save(region);
        Assertions.assertNotNull(regionSaved, String.format("Region saved should not be null"));
        Region regionFound = regionCrudUseCase.find(regionSaved.getId());
        Assertions.assertEquals(regionSaved, regionFound, String.format("Region should be equals"));
    }

    @Test
    @DisplayName("Finds an Region")
    public void test03() {
        Region regionFound = regionCrudUseCase.find(region.getId());
        Assertions.assertNotNull(regionFound, String.format("Region should not be null"));
    }

    @Test
    @DisplayName("Finds all Regions")
    public void test04() {
        Collection<Region> regions = regionCrudUseCase.findAll();
        Assertions.assertNotNull(regions, String.format("Regions should not be null"));
        Assertions.assertEquals(2, regions.size(), String.format("Regions size doesn't match"));
    }

    @Test
    @DisplayName("Updates an Region")
    public void test05() {

        region.setName(region.getName() + "updated");

        regionCrudUseCase.update(region);

        Region regionUpdated = regionCrudUseCase.find(region.getId());

        Assertions.assertNotNull(regionUpdated, String.format("Region should not be null"));
        Assertions.assertEquals(regionUpdated.getName(), region.getName(), String.format("Region name should be updated"));
    }

    @Test
    @DisplayName("Deletes an Region")
    public void test06() {

        regionCrudUseCase.delete(region.getId());

        Region regionUpdated = regionCrudUseCase.find(region.getId());

        Assertions.assertNull(regionUpdated, String.format("Region should be null"));
    }
}
