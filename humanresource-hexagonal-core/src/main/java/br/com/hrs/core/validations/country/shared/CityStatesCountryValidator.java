package br.com.hrs.core.validations.country.shared;

import br.com.hrs.core.exception.HrsBusinessException;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.validations.SaveValidator;
import br.com.hrs.core.validations.UpdateValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

@Named
public class CityStatesCountryValidator implements SaveValidator<Country>, UpdateValidator<Country> {

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