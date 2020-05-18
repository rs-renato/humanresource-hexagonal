package br.com.hrs.core.usecase.impl.region;

import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.UpdateUseCase;
import br.com.hrs.core.validator.UpdateValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class RegionUpdateUseCase extends UpdateUseCase<Region, Integer> {

    @Inject
    public RegionUpdateUseCase(Repository<Region, Integer> repository, UpdateValidator<Region>...validators) {
        super(repository, validators);
    }
}