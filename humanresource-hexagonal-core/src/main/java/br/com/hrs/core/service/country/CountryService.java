package br.com.hrs.core.service.country;

import java.util.Collection;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.usecase.country.DeleteCountryUseCase;
import br.com.hrs.core.usecase.country.FindCountryUseCase;
import br.com.hrs.core.usecase.country.SaveCountryUseCase;
import br.com.hrs.core.usecase.country.UpdateCountryUseCase;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CountryService {

	private static final String COUNTRY_NOT_FOUND_MESSAGE 		= "Country not found";
	private static final String COUNTRIES_NOT_FOUND_MESSAGE 	= "Countries not found";
	
	private FindCountryUseCase findCountryUseCase;
	private SaveCountryUseCase saveCountryUseCase;
	private UpdateCountryUseCase updateCountryUseCase;
	private DeleteCountryUseCase deleteCountryUseCase;

	@Inject
	public CountryService(FindCountryUseCase findCountryUseCase, SaveCountryUseCase saveCountryUseCase,
			UpdateCountryUseCase updateCountryUseCase, DeleteCountryUseCase deleteCountryUseCase) {

		this.findCountryUseCase = findCountryUseCase;
		this.saveCountryUseCase = saveCountryUseCase;
		this.updateCountryUseCase = updateCountryUseCase;
		this.deleteCountryUseCase = deleteCountryUseCase;
	}

	public String save(Country country) {
		return this.saveCountryUseCase.save(country);
	}
	
	public String update(Country country) {
		return updateCountryUseCase.update(country);
	}

	public void delete(String id) {
		this.deleteCountryUseCase.delete(id);
	}

	public Collection<Country> findAll(){
		return this.findCountryUseCase.findAll();
	}
	
	public Country find(String id) {
		return this.findCountryUseCase.find(id);
	}
}