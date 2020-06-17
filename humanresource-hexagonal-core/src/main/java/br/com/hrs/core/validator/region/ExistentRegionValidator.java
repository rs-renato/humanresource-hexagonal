package br.com.hrs.core.validator.region;

import br.com.hrs.core.exception.HrsNotFoundException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.RegionRepository;
import br.com.hrs.core.validator.DeleteValidator;
import br.com.hrs.core.validator.UpdateValidator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class ExistentRegionValidator implements UpdateValidator<Region>, DeleteValidator<Region> {

	private RegionRepository regionRepository;

	@Inject
	public ExistentRegionValidator(RegionRepository regionRepository) {
		this.regionRepository = regionRepository;
	}
	
	@Override
	public void validate(Region region) {
		logger.debug("Validating if region exists");

		if (Objects.isNull(region)) {
			Error.of("Region").when(FIELD.MANDATORY).trows();
		}

		if (!this.regionRepository.existsById(region.getId())) {
			throw new HrsNotFoundException("Region does not exist");
		}
	}
}