package br.com.hrs.core.validator.location;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Location;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;
import br.com.hrs.core.validator.country.ExistentCountryValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class ExistentCountryLocationValidator implements SaveValidator<Location>, UpdateValidator<Location>{

	private static Logger logger = LogManager.getLogger(ExistentCountryLocationValidator.class);

	private ExistentCountryValidator existentCountryValidator;

	@Inject
	public ExistentCountryLocationValidator(ExistentCountryValidator existentCountryValidator) {
		this.existentCountryValidator = existentCountryValidator;
	}

	@Override
	public void validate(Location location) {
		logger.debug("Validating if location's country is existent");

		if (Objects.isNull(location)) {
			Error.of("Location").when(FIELD.MANDATORY).trows();
		}

		this.existentCountryValidator.validate(location.getCountry());
	}
}