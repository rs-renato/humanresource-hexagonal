package br.com.hrs.core.usecase.country;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validations.country.SaveCountryValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SaveCountryUseCase {
	
	private static Logger logger = LogManager.getLogger(SaveCountryUseCase.class);

	private List<SaveCountryValidator> validators;
	private Repository<Country, String> countryRepository;

	@Inject
	public SaveCountryUseCase( Repository<Country, String> countryRepository, SaveCountryValidator ...validators) {
		this.validators = validators != null ? new LinkedList<>(Arrays.asList(validators)) : new ArrayList<>();
		this.countryRepository = countryRepository;
	}
	
	public String save(Country country) {
		logger.debug("Processing {} validations to save country {}", validators.size(), country.getName());
		this.validators.forEach(validator -> validator.validate(country));
		logger.debug("Calling save on country repository");
		return this.countryRepository.save(country);
	}
}
