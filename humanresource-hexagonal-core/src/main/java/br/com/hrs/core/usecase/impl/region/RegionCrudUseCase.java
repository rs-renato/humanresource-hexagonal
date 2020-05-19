package br.com.hrs.core.usecase.impl.region;

import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.CrudUseCase;
import br.com.hrs.core.validator.Validator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class RegionCrudUseCase extends CrudUseCase<Region, Integer> {

	@Inject
	public RegionCrudUseCase(Repository<Region, Integer> repository, Validator<Region>... validators) {
		super(repository, validators);
	}
}