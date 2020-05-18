package br.com.hrs.core.usecase.impl.region;

import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.DeleteUseCase;
import br.com.hrs.core.validator.DeleteValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class RegionDeleteUseCase extends DeleteUseCase<Region, Integer> {

	@Inject
	public RegionDeleteUseCase(Repository<Region, Integer> repository, DeleteValidator<Region>... validators) {
		super(repository, validators);
	}
}