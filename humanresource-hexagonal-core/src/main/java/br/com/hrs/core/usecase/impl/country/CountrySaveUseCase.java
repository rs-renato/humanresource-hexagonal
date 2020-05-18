package br.com.hrs.core.usecase.impl.country;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.SaveUseCase;
import br.com.hrs.core.validator.SaveValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CountrySaveUseCase extends SaveUseCase<Country, String> {

	@Inject
	public CountrySaveUseCase(Repository<Country, String> repository, SaveValidator<Country>... validators) {
		super(repository, validators);
	}
}
