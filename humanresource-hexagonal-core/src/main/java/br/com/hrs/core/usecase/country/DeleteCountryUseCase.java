package br.com.hrs.core.usecase.country;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validations.country.DeleteCountryValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DeleteCountryUseCase {

	private static Logger logger = LogManager.getLogger(DeleteCountryUseCase.class);

	private Repository<Country, String> countryRepository;
	private List<DeleteCountryValidator> validators;

	@Inject
	public DeleteCountryUseCase(Repository<Country, String> countryRepository, DeleteCountryValidator ...validators) {
		this.validators = validators != null ? new LinkedList<>(Arrays.asList(validators)) : new ArrayList<>();
		this.countryRepository = countryRepository;
	}
	
	public void delete(Country country) {
		delete(country.getId());
	}
	
	public void delete(String id) {
		logger.debug("Processing {} validations to delete country id {}", validators.size(), id);
		this.validators.forEach(validator -> validator.validate(new Country.Builder().id(id).build()));
		logger.debug("Calling delete on country repository");
		this.countryRepository.delete(id);
	}
}
