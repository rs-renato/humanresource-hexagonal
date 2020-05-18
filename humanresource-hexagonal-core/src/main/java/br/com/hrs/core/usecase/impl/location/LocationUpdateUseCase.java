package br.com.hrs.core.usecase.impl.location;

import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.UpdateUseCase;
import br.com.hrs.core.validator.UpdateValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LocationUpdateUseCase extends UpdateUseCase<Location, Integer> {

    @Inject
    public LocationUpdateUseCase(Repository<Location, Integer> repository, UpdateValidator<Location>...validators) {
        super(repository, validators);
    }
}