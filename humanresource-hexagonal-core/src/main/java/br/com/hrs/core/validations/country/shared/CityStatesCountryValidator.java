package br.com.hrs.core.validations.country.shared;

import java.util.Arrays;
import java.util.List;

import br.com.hrs.core.exception.HrsBusinessException;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.validations.country.SaveCountryValidator;
import br.com.hrs.core.validations.country.UpdateCountryValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Named;

@Named
public class CityStatesCountryValidator implements SaveCountryValidator, UpdateCountryValidator {

	private static Logger logger = LogManager.getLogger(CityStatesCountryValidator.class);
	
	private static final List<String> CITY_STATES =  Arrays.asList("MONACO", "VATICAN", "SINGAPORE");
	
	@Override
	public void validate(Country country) {
		logger.debug("Validating if country is an city state");
		if(CITY_STATES.contains(country.getName().toUpperCase())) {
			throw new HrsBusinessException("City-States are not allowed");
		}
	}
}