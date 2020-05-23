package br.com.hrs.core.usecase;

import br.com.hrs.core.model.EntityKey;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validator.DeleteValidator;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;
import br.com.hrs.core.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.*;

public abstract class CrudUseCase<E extends EntityKey<ID>, ID>{

    static Logger logger = LogManager.getLogger(CrudUseCase.class);

    protected Repository<E, ID> repository;
    protected List<Validator> validators;

    @Inject
    public CrudUseCase(Repository<E, ID> repository, Validator<E> ...validators) {
        this.validators = validators != null ? new LinkedList<>(Arrays.asList(validators)) : new ArrayList<>();
        this.repository = repository;
    }

    public boolean exists(ID id) {
        logger.debug("Calling exists in repository");
        return this.repository.exists(id);
    }

    public E find(ID id) {
        logger.debug("Calling find in repository");
        return this.repository.find(id);
    }

    public Collection<E> findAll() {
        logger.debug("Calling findAll in repository");
        return this.repository.findAll();
    }

    public E save(E entity) {
        this.validators.stream()
                .filter(validator -> validator instanceof SaveValidator)
                .forEach(validator -> validator.validate(entity));
        logger.debug("Calling save on repository");
        return this.repository.save(entity);
    }

    public void update(E entity) {
        this.validators.stream()
                .filter(validator -> validator instanceof UpdateValidator)
                .forEach(validator -> validator.validate(entity));
        logger.debug("Calling save on repository");
        this.repository.update(entity);
    }

    public void delete(ID id) {
        this.validators.stream()
                .filter(validator -> validator instanceof DeleteValidator)
                .forEach(validator -> validator.validate(id));
        logger.debug("Calling delete on repository");
        this.repository.delete(id);
    }
}