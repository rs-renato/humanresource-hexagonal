package br.com.hrs.core.usecase.impl.country;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.DeleteUseCase;
import br.com.hrs.core.validator.DeleteValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CountryDeleteUseCase extends DeleteUseCase<Country, String> {

	@Inject
	public CountryDeleteUseCase(Repository<Country, String> repository, DeleteValidator<Country>... validators) {
		super(repository, validators);
	}
}