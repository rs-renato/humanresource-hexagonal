package br.com.hrs.core.usecase.region;

import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.RegionRepository;
import br.com.hrs.core.usecase.CrudAbstractUseCaseImpl;
import br.com.hrs.core.validator.Validator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Named
class RegionCrudUseCase extends CrudAbstractUseCaseImpl<Region, Integer> implements RegionUseCase {

	private RegionRepository regionRepository;
	private List<Validator<Region>> validators;

	@Inject
	public RegionCrudUseCase(RegionRepository regionRepository, Validator<Region>... validators) {
		this.regionRepository = regionRepository;
		this.validators = validators != null ? new LinkedList<>(Arrays.asList(validators)) : new ArrayList<>();
	}

	@Override
	protected RegionRepository getRepository() {
		return this.regionRepository;
	}

	@Override
	protected List<Validator<Region>> getValidators() {
		return validators;
	}
}