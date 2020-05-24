package br.com.hrs.core.validator.country;

import br.com.hrs.core.exception.HrsBusinessException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validator.SaveValidator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class UniqueIdCountryValidator implements SaveValidator<Country> {

	private Repository<Country, String> countryRepository;

	@Inject
	public UniqueIdCountryValidator(Repository<Country, String> countryRepository) {
		this.countryRepository = countryRepository;
	}
	
	@Override
	public void validate(Country country) {

		logger.debug("Validating if country has an unique Id");

		if (Objects.isNull(country)) {
			Error.of("Country").when(FIELD.MANDATORY).trows();
		}

		if (this.countryRepository.existsById(country.getId())) {
			throw new HrsBusinessException("Country id should be unique");
		}
	}
}