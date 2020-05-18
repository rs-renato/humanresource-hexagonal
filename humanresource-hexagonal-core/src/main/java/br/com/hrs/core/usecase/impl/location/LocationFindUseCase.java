package br.com.hrs.core.usecase.impl.location;

import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.FindUseCase;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LocationFindUseCase extends FindUseCase<Location, Integer> {

    @Inject
    public LocationFindUseCase(Repository<Location, Integer> repository) {
        super(repository);
    }
}
