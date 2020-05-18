package br.com.hrs.core.validations.impl.country.save;

import br.com.hrs.core.exception.HrsBusinessException;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validations.SaveValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UniqueIdCountryValidator implements SaveValidator<Country> {

	private static Logger logger = LogManager.getLogger(UniqueIdCountryValidator.class);

	private Repository<Country, String> countryRepository;

	@Inject
	public UniqueIdCountryValidator(Repository<Country, String> countryRepository) {
		this.countryRepository = countryRepository;
	}
	
	@Override
	public void validate(Country country) {
		logger.debug("Validating if country has an unique Id");
		if (this.countryRepository.exists(country.getId())) {
			throw new HrsBusinessException("Country id should be unique");
		}
	}
}