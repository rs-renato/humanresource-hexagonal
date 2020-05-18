package br.com.hrs.core.usecase;

import br.com.hrs.core.model.EntityKey;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validations.SaveValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class SaveUseCase <E extends EntityKey<ID>, ID> {
	
	private static Logger logger = LogManager.getLogger(SaveUseCase.class);

	protected List<SaveValidator<E>> validators;
	protected Repository<E, ID> repository;

	@Inject
	public SaveUseCase(Repository<E, ID> repository, SaveValidator<E> ...validators) {
		this.validators = validators != null ? new LinkedList<>(Arrays.asList(validators)) : new ArrayList<>();
		this.repository = repository;
	}
	
	public ID save(E entity) {
		logger.debug("Processing {} validations to save entity {}", validators.size(), entity);
		this.validators.forEach(validator -> validator.validate(entity));
		logger.debug("Calling save on entity repository");
		return this.repository.save(entity);
	}
}
