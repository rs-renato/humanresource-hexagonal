package br.com.hrs.core.usecase.location;

import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.LocationRepository;
import br.com.hrs.core.usecase.CrudAbstractUseCaseImpl;
import br.com.hrs.core.validator.Validator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Named
class LocationCrudUseCase extends CrudAbstractUseCaseImpl<Location, Integer> implements LocationUseCase {

	private LocationRepository locationRepository;
	private List<Validator<Location>> validators;

	@Inject
	public LocationCrudUseCase(LocationRepository locationRepository, Validator<Location>... validators) {
		this.locationRepository = locationRepository;
		this.validators = validators != null ? new LinkedList<>(Arrays.asList(validators)) : new ArrayList<>();
	}

	@Override
	protected LocationRepository getRepository() {
		return this.locationRepository;
	}

	@Override
	protected List<Validator<Location>> getValidators() {
		return validators;
	}
}