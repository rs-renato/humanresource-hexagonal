package br.com.hrs.core.repository.mock;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.CountryRepository;
import br.com.hrs.core.repository.Repository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Named
public class CountryMockRepository extends MockRepository<Country, String> implements CountryRepository {

    private Repository<Region, Integer> regionRepository;

    @Inject
    public CountryMockRepository(Repository<Region, Integer> regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public List<Country> findByRegionId(Integer regionId) {
        logger.debug("{}: Fake repository -> findByRegionId({}})", REPOSITORY_NAME,regionId);

        if (Objects.isNull(regionId)) {
            Error.of("ID").when(FIELD.MANDATORY).trows();
        }

        return this.regionRepository.find(regionId).getCountries();
    }

    @Override
    public Collection<Country> buildCollection() {

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
