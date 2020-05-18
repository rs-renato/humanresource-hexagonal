package br.com.hrs.core.usecase.impl.country;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.UpdateUseCase;
import br.com.hrs.core.validator.UpdateValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CuntryUpdateUseCase extends UpdateUseCase<Country, String> {

    @Inject
    public CuntryUpdateUseCase(Repository<Country, String> repository, UpdateValidator<Country>...validators) {
        super(repository, validators);
    }
}