package br.com.hrs.core.usecase.country;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.CountryRepository;
import br.com.hrs.core.usecase.CrudAbstractUseCaseImpl;
import br.com.hrs.core.validator.Validator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

@Named
class CountryCrudUseCase extends CrudAbstractUseCaseImpl<Country, String> implements CountryUseCase {

	private CountryRepository countryRepository;
	private List<Validator<Country>> validators;

	@Inject
	public CountryCrudUseCase(CountryRepository countryRepository, Validator<Country>... validators) {
		this.countryRepository = countryRepository;
		this.validators = validators != null ? new LinkedList<>(Arrays.asList(validators)) : new ArrayList<>();
	}

	@Override
	protected CountryRepository getRepository() {
		return this.countryRepository;
	}

	@Override
	protected List<Validator<Country>> getValidators() {
		return validators;
	}

	@Override
	public Optional<List<Country>> findByRegionId(Integer regionId){
		return getRepository().findByRegionId(regionId);
	}
}