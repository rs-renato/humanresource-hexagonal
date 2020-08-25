package br.com.hrs.core.usecase;

import br.com.hrs.core.model.EntityKey;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.repository.filter.Filter;
import br.com.hrs.core.repository.pagination.Pagination;
import br.com.hrs.core.validator.DeleteValidator;
import br.com.hrs.core.validator.SaveValidator;
import br.com.hrs.core.validator.UpdateValidator;
import br.com.hrs.core.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public abstract class CrudAbstractUseCaseImpl<E extends EntityKey<ID>, ID> implements CrudUseCase<E, ID>{

    protected static final Logger logger = LogManager.getLogger(CrudAbstractUseCaseImpl.class);

    protected abstract List<Validator<E>> getValidators();
    protected abstract Repository<E, ID> getRepository();

    @Override
    public long count() {
        logger.debug("Calling count in repository");
        return getRepository().count();
    }

    public boolean existsById(ID id) {
        logger.debug("Calling exists in repository");
        return getRepository().existsById(id);
    }

    public Optional<E> findById(ID id) {
        logger.debug("Calling find in repository");
        return getRepository().findById(id);
    }

    public List<E> findAll() {
        logger.debug("Calling findAll in repository");
        return getRepository().findAll();
    }

    @Override
    public List<E> findAll(Pagination pagination) {
        logger.debug("Calling findAll paginated in repository");
        return getRepository().findAll(pagination);
    }

    @Override
    public List<E> findAll(Filter<E> filter) {
        logger.debug("Calling findAll filtered in repository");
        return getRepository().findAll(filter);
    }

    public E save(E entity) {
       getValidators().stream()
                .filter(validator -> validator instanceof SaveValidator)
                .forEach(validator -> validator.validate(entity));
        logger.debug("Calling save on repository");
        return getRepository().save(entity);
    }

    public void update(E entity) {
       getValidators().stream()
                .filter(validator -> validator instanceof UpdateValidator)
                .forEach(validator -> validator.validate(entity));
        logger.debug("Calling save on repository");
        getRepository().update(entity);
    }

    public void deleteById(ID id) {
       Optional<E> entity = getRepository().findById(id);
       getValidators().stream()
                .filter(validator -> validator instanceof DeleteValidator)
                .forEach(validator -> validator.validate(entity));
        logger.debug("Calling delete on repository");
        getRepository().deleteById(id);
    }
}