package br.com.hrs.core.repository.mock;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.CountryRepository;
import br.com.hrs.core.repository.RegionRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Named
public class CountryMockRepository extends MockRepository<Country, String> implements CountryRepository {

    private RegionRepository regionRepository;

    @Inject
    public CountryMockRepository(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public Optional<List<Country>> findByRegionId(Integer regionId) {
        logger.debug("{}: Fake repository -> findByRegionId({}})", REPOSITORY_NAME,regionId);

        if (Objects.isNull(regionId)) {
            Error.of("ID").when(FIELD.MANDATORY).trows();
        }

        Optional<Region> region = this.regionRepository.findById(regionId);
        return region.isPresent() ? Optional.of(region.get().getCountries()) : Optional.empty();
    }

    @Override
    public List<Country> buildCollection() {

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

        Country country03 = new Country.Builder()
                .id("US")
                .name("United States")
                .region(new Region.Builder().id(4).build())
                .build();

        return Arrays.asList(country01, country02, country03);
    }
}
