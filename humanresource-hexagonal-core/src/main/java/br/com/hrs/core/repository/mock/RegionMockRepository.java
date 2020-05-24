package br.com.hrs.core.repository.mock;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.RegionRepository;

import javax.inject.Named;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Named
public class RegionMockRepository extends MockRepository<Region, Integer> implements RegionRepository {

    @Override
    public Region findByName(String regionName) {

        logger.debug("{}: Fake repository -> findByName({}})", REPOSITORY_NAME, regionName);

        if (Objects.isNull(regionName)) {
            Error.of("Region Name").when(FIELD.MANDATORY).trows();
        }

        Optional<Region> region = findAll().stream()
                .filter(reg -> reg.getName().equals(regionName))
                .findFirst();

        return region.isPresent() ? region.get() : null;
    }

    @Override
    public Collection<Region> buildCollection() {

        Country country01 = new Country.Builder()
                .id("IT")
                .name("Italy")
                .region(new Region.Builder().id(1).build())
                .build();

        Country country02 = new Country.Builder()
                .id("JP")
                .name("Japan")
                .region(new Region.Builder().id(3).build())
                .build();

        Region region01 = new Region.Builder()
                            .id(1)
                            .name("Europe")
                            .countries(Arrays.asList(country01))
                        .build();

        Region region02 = new Region.Builder()
                .id(2)
                .name("Americas")
                .countries(Arrays.asList(country02))
                .build();

        return Arrays.asList(region01, region02);
    }
}
