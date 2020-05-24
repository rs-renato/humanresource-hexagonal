package br.com.hrs.core.validator.country;

import br.com.hrs.core.exception.HrsNotFoundException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class ExistentRegionCountryValidator implements SaveValidator<Country>, UpdateValidator<Country> {

	private Repository<Region, Integer> regionRepository;

	@Inject
	public ExistentRegionCountryValidator(Repository<Region, Integer> regionRepository) {
		this.regionRepository = regionRepository;
	}

	@Override
	public void validate(Country country) {

		logger.debug("Validating if region's country is existent");

		if (Objects.isNull(country)) {
			Error.of("Country").when(FIELD.MANDATORY).trows();
		}

		Region region = country.getRegion();
		if (region == null || !regionRepository.existsById(region.getId())) {
			throw new HrsNotFoundException("Region does not exist");
		}
	}
}