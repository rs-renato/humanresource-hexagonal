package br.com.hrs.core.repository;

import br.com.hrs.core.model.Job;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public abstract class Repository<E, ID> {

    protected Logger logger = LogManager.getLogger(Repository.class);

    public abstract E find(ID id);

    public abstract ID save(E entity);

    public abstract boolean update(E entity);

    public abstract Collection<E> findAll();

    public abstract boolean delete(ID id);

    public abstract boolean exists(ID id);
}
