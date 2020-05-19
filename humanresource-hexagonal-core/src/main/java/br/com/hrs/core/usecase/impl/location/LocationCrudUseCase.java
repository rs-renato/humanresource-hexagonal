package br.com.hrs.core.usecase.impl.location;

import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.CrudUseCase;
import br.com.hrs.core.validator.Validator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LocationCrudUseCase extends CrudUseCase<Location, Integer> {

	@Inject
	public LocationCrudUseCase(Repository<Location, Integer> repository, Validator<Location>... validators) {
		super(repository, validators);
	}
}