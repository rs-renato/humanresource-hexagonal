package br.com.hrs.core.usecase;

import br.com.hrs.core.model.EntityKey;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.validator.DeleteValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class DeleteUseCase<E extends EntityKey<ID>, ID>{

    static Logger logger = LogManager.getLogger(DeleteUseCase.class);

    protected Repository<E, ID> repository;
    protected List<DeleteValidator> validators;

    @Inject
    public DeleteUseCase(Repository<E, ID> repository, DeleteValidator<E> ...validators) {
        this.validators = validators != null ? new LinkedList<>(Arrays.asList(validators)) : new ArrayList<>();
        this.repository = repository;
    }

    public boolean delete(ID id) {
        logger.debug("Processing {} validations to delete country id {}", validators.size(), id);
        this.validators.forEach(validator -> validator.validate(id));
        logger.debug("Calling delete on country repository");
        return this.repository.delete(id);
    }
}