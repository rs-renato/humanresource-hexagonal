package br.com.hrs.core.usecase.impl.location;

import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.SaveUseCase;
import br.com.hrs.core.validator.SaveValidator;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LocationSaveUseCase extends SaveUseCase<Location, Integer> {
	
	@Inject
	public LocationSaveUseCase(Repository<Location, Integer> repository, SaveValidator<Location>... validators) {
		super(repository, validators);
	}
}
