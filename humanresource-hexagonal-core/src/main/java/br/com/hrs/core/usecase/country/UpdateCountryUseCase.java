package br.com.hrs.core.usecase.country;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validations.country.UpdateCountryValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UpdateCountryUseCase {

	private static Logger logger = LogManager.getLogger(UpdateCountryUseCase.class);

	private List<UpdateCountryValidator> validators;
	private Repository<Country, String> countryRepository;

	@Inject
	public UpdateCountryUseCase( Repository<Country, String> countryRepository, UpdateCountryValidator ...validators) {
		this.validators = validators != null ? new LinkedList<>(Arrays.asList(validators)) : new ArrayList<>();
		this.countryRepository = countryRepository;
	}
	
	public String update(Country country) {
		logger.debug("Processing {} validations to update country id {}", validators.size(), country.getId());
		this.validators.forEach(validator -> validator.validate(country));
		logger.debug("Calling save on country repository");
		return this.countryRepository.save(country);
	}
}