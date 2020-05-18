package br.com.hrs.core.usecase.impl.country;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.FindUseCase;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CountryFindUseCase extends FindUseCase<Country, String> {

    @Inject
    public CountryFindUseCase(Repository<Country, String> repository) {
        super(repository);
    }
}
