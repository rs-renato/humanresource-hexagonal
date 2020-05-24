package br.com.hrs.core.usecase.region;

import br.com.hrs.core.HrsBuildConfiguration;
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
@DisplayName("Region Crud Use Case")
public class RegionCrudUseCaseTest {

    @Inject
    private RegionUseCase regionCrudUseCase;

    private static Region region;

    @BeforeAll
    public static void setUp() {
        region = new Region.Builder()
                .id(1)
                .name("New Region")
                .build();
    }

    @Test
    @DisplayName("Exists an Regions")
    public void test01() {
        boolean exists = regionCrudUseCase.existsById(region.getId());
        Assertions.assertTrue(exists, String.format("Region should exist"));
    }

    @Test
    @DisplayName("Saves an Region")
    public void test02() {
        Region region = new Region("new region");
        Region regionSaved = regionCrudUseCase.save(region);
        Assertions.assertNotNull(regionSaved, String.format("Region saved should not be null"));
        Assertions.assertEquals(region.getName(), regionSaved.getName(), String.format("Region should be equals"));
    }

    @Test
    @DisplayName("Finds an Region")
    public void test03() {
        Optional<Region> regionFound = regionCrudUseCase.findById(region.getId());
        Assertions.assertTrue(regionFound.isPresent(), String.format("Region should not be null"));
    }

    @Test
    @DisplayName("Finds all Regions")
    public void test04() {
        Collection<Region> regions = regionCrudUseCase.findAll();
        Assertions.assertNotNull(regions, String.format("Regions should not be null"));
        Assertions.assertEquals(3, regions.size(), String.format("Regions size doesn't match"));
    }

    @Test
    @DisplayName("Updates an Region")
    public void test05() {

        region = new Region.Builder()
                .id(1)
                .name("name updated")
                .build();

        regionCrudUseCase.update(region);

        Optional<Region> regionOpt = regionCrudUseCase.findById(region.getId());

        Assertions.assertTrue(regionOpt.isPresent(), String.format("Region should not be null"));
        Assertions.assertEquals(regionOpt.get().getName(), region.getName(), String.format("Region name should be updated"));
    }

    @Test
    @DisplayName("Deletes an Region")
    public void test06() {

        regionCrudUseCase.deleteById(region.getId());

        Optional<Region> regionOpt = regionCrudUseCase.findById(region.getId());

        Assertions.assertFalse(regionOpt.isPresent(), String.format("Region should be null"));
    }
}
