package br.com.hrs.core.usecase.impl.region;

import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.SaveUseCase;
import br.com.hrs.core.validator.SaveValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class RegionSaveUseCase extends SaveUseCase<Region, Integer> {
	
	@Inject
	public RegionSaveUseCase(Repository<Region, Integer> repository, SaveValidator<Region>... validators) {
		super(repository, validators);
	}
}
