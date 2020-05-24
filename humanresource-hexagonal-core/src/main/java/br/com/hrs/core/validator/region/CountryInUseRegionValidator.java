package br.com.hrs.core.validator.region;

import br.com.hrs.core.exception.HrsBusinessException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validator.DeleteValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Named
public class CountryInUseRegionValidator implements DeleteValidator<Region> {

	private static Logger logger = LogManager.getLogger(CountryInUseRegionValidator.class);

	private Repository<Region, Integer> regionRepository;

	@Inject
	public CountryInUseRegionValidator(Repository<Region, Integer> regionRepository) {
		this.regionRepository = regionRepository;
	}
	
	@Override
	public void validate(Region region) {
		logger.debug("Validating if region has any country associated on it");

		if (Objects.isNull(region)) {
			Error.of("Region").when(FIELD.MANDATORY).trows();
		}

		Optional<Region> regionFound = this.regionRepository.findById(region.getId());
		List<Country> countries = regionFound.get().getCountries();
		if(countries != null && !countries.isEmpty()) {
			throw new HrsBusinessException("Region could not be deleted because it has associated countries");
		}
	}
}
