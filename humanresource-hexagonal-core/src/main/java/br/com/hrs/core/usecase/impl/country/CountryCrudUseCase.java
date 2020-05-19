package br.com.hrs.core.usecase.impl.country;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.CrudUseCase;
import br.com.hrs.core.validator.Validator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CountryCrudUseCase extends CrudUseCase<Country, String> {

	@Inject
	public CountryCrudUseCase(Repository<Country, String> repository, Validator<Country>... validators) {
		super(repository, validators);
	}
}