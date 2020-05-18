package br.com.hrs.core.validator.impl.country.shared;

import br.com.hrs.core.exception.HrsBusinessException;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ExistentRegionCountryValidator implements SaveValidator<Country>, UpdateValidator<Country> {

	private static Logger logger = LogManager.getLogger(ExistentRegionCountryValidator.class);

	private Repository<Region, Integer> regionRepository;

	@Inject
	public ExistentRegionCountryValidator(Repository<Region, Integer> regionRepository) {
		this.regionRepository = regionRepository;
	}

	@Override
	public void validate(Country country) {
		Region region = country.getRegion();
		logger.debug("Validating if region's country is existent");
		if (region == null || !regionRepository.exists(region.getId())) {
			throw new HrsBusinessException("Region does not exist");
		}
	}
}