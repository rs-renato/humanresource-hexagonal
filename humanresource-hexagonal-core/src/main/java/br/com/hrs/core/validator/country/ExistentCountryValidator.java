package br.com.hrs.core.validator.country;

import br.com.hrs.core.exception.HrsNotFoundException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.CountryRepository;
import br.com.hrs.core.validator.DeleteValidator;
import br.com.hrs.core.validator.UpdateValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.Objects;

public class ExistentCountryValidator implements UpdateValidator<Country>, DeleteValidator<Country> {

    private static final Logger logger = LogManager.getLogger(ExistentCountryValidator.class);

    private CountryRepository countryRepository;

    @Inject
    public ExistentCountryValidator(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void validate(Country country) {

        logger.debug("Validating if country is existent");

        if (Objects.isNull(country)) {
            Error.of("Country").when(FIELD.MANDATORY).trows();
        }

        if (!countryRepository.existsById(country.getId())) {
            throw new HrsNotFoundException("Country does not exist");
        }
    }
}
