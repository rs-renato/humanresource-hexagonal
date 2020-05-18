package br.com.hrs.core.usecase.impl.location;

import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.DeleteUseCase;
import br.com.hrs.core.validator.DeleteValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LocationDeleteUseCase extends DeleteUseCase<Location, Integer> {

	@Inject
	public LocationDeleteUseCase(Repository<Location, Integer> repository, DeleteValidator<Location>... validators) {
		super(repository, validators);
	}
}