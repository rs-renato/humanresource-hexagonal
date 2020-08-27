package br.com.hrs.core.repository.mock;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.CountryRepository;
import br.com.hrs.core.repository.RegionRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

@Named
public class CountryMockRepository extends MockRepository<Country, String> implements CountryRepository {

    private RegionRepository regionRepository;

    @Inject
    public CountryMockRepository(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public Optional<List<Country>> findAllByRegionIdIn(Set<Integer> regionIds) {
        logger.debug("{}: Fake repository -> findByRegionId({}})", REPOSITORY_NAME,regionIds);

        if (Objects.isNull(regionIds)) {
            Error.of("ID").when(FIELD.MANDATORY).trows();
        }

        List<Country> results = new ArrayList<>();

        for (Integer id : regionIds) {
            this.regionRepository.findById(id).ifPresent(region -> results.addAll(region.getCountries()));
        }

        return results.isEmpty() ? Optional.empty() :  Optional.of(results);
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
