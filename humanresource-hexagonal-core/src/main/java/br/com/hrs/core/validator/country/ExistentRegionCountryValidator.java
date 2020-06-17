package br.com.hrs.core.validator.country;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;
import br.com.hrs.core.validator.region.ExistentRegionValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class ExistentRegionCountryValidator implements SaveValidator<Country>, UpdateValidator<Country> {

	private static final Logger logger = LogManager.getLogger(ExistentRegionCountryValidator.class);

	private ExistentRegionValidator existentRegionValidator;

	@Inject
	public ExistentRegionCountryValidator(ExistentRegionValidator existentRegionValidator) {
		this.existentRegionValidator = existentRegionValidator;
	}

	@Override
	public void validate(Country country) {

		logger.debug("Validating if region's country is existent");

		if (Objects.isNull(country)) {
			Error.of("Country").when(FIELD.MANDATORY).trows();
		}

		this.existentRegionValidator.validate(country.getRegion());
	}
}