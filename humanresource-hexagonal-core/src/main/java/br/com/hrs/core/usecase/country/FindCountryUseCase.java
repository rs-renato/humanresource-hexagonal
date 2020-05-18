package br.com.hrs.core.usecase.country;

import java.util.Collection;
import java.util.List;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class FindCountryUseCase {

	private static Logger logger = LogManager.getLogger(FindCountryUseCase.class);

	private Repository<Country, String> countryRepository;

	@Inject
	public FindCountryUseCase( Repository<Country, String> countryRepository) {
		this.countryRepository = countryRepository;
	}
	
	public boolean exists(String id) {
		logger.debug("Calling exists on country repository");
		return this.countryRepository.exists(id);
	}
	
	public Country find(String id) {
		logger.debug("Calling findOne on country repository");
		return this.countryRepository.find(id);
	}
	
	public Collection<Country> findAll() {
		logger.debug("Calling findAll on country repository");
		return this.countryRepository.findAll();
	}
}