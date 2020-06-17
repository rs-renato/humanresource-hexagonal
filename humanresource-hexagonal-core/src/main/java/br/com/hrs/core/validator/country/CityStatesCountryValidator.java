package br.com.hrs.core.validator.country;

import br.com.hrs.core.exception.HrsBusinessException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Named;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Named
public class CityStatesCountryValidator implements SaveValidator<Country>, UpdateValidator<Country> {

	private static final Logger logger = LogManager.getLogger(CityStatesCountryValidator.class);

	private static final List<String> CITY_STATES =  Arrays.asList("MONACO", "VATICAN", "SINGAPORE");
	
	@Override
	public void validate(Country country) {

		logger.debug("Validating if country is an city state");

		if (Objects.isNull(country)) {
			Error.of("Country").when(FIELD.MANDATORY).trows();
		}

		if(CITY_STATES.contains(country.getName().toUpperCase())) {
			throw new HrsBusinessException("City-States are not allowed");
		}
	}
}