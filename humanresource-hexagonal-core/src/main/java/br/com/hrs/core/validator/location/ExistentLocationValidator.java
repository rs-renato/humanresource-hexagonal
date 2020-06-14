package br.com.hrs.core.validator.location;

import br.com.hrs.core.exception.HrsNotFoundException;
import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.LocationRepository;
import br.com.hrs.core.validator.DeleteValidator;
import br.com.hrs.core.validator.UpdateValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class ExistentLocationValidator implements UpdateValidator<Location>, DeleteValidator<Location> {

	private static Logger logger = LogManager.getLogger(ExistentLocationValidator.class);

	private LocationRepository locationRepository;

	@Inject
	public ExistentLocationValidator(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}
	
	@Override
	public void validate(Location location) {
		logger.debug("Validating if location has unique name");

		if (Objects.isNull(location)) {
			Error.of("Location").when(FIELD.MANDATORY).trows();
		}

		if (!this.locationRepository.existsById(location.getId())) {
			throw new HrsNotFoundException("Location does not exist");
		}
	}
}