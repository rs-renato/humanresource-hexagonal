package br.com.hrs.core.usecase.impl.region;

import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.FindUseCase;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class RegionFindUseCase extends FindUseCase<Region, Integer> {

    @Inject
    public RegionFindUseCase(Repository<Region, Integer> repository) {
        super(repository);
    }
}
