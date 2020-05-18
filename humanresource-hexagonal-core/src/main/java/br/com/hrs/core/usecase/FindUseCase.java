package br.com.hrs.core.usecase;

import br.com.hrs.core.model.EntityKey;
import br.com.hrs.core.repository.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

public abstract class FindUseCase <E extends EntityKey<ID>, ID> {

	private static Logger logger = LogManager.getLogger(FindUseCase.class);

	protected Repository<E, ID> repository;

	@Inject
	public FindUseCase(Repository<E, ID> repository) {
		this.repository = repository;
	}
	
	public boolean exists(ID id) {
		logger.debug("Calling exists on country repository");
		return this.repository.exists(id);
	}
	
	public E find(ID id) {
		logger.debug("Calling findOne on country repository");
		return this.repository.find(id);
	}
	
	public Collection<E> findAll() {
		logger.debug("Calling findAll on country repository");
		return this.repository.findAll();
	}
}