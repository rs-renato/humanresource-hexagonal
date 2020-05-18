package br.com.hrs.core.usecase;

import br.com.hrs.core.model.EntityKey;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validations.UpdateValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class UpdateUseCase <E extends EntityKey<ID>, ID> {

	private static Logger logger = LogManager.getLogger(UpdateUseCase.class);

	protected List<UpdateValidator<E>> validators;
	protected Repository<E, ID> repository;

	@Inject
	public UpdateUseCase(Repository<E, ID> repository, UpdateValidator<E> ...validators) {
		this.validators = validators != null ? new LinkedList<>(Arrays.asList(validators)) : new ArrayList<>();
		this.repository = repository;
	}
	
	public boolean update(E entity) {
		logger.debug("Processing {} validations to update entity id {}", validators.size(), entity.getId());
		this.validators.forEach(validator -> validator.validate(entity));
		logger.debug("Calling save on entity repository");
		return this.repository.update(entity);
	}
}