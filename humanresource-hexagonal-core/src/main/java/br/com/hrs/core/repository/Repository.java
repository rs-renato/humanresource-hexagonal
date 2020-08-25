package br.com.hrs.core.repository;

import br.com.hrs.core.model.EntityKey;
import br.com.hrs.core.repository.filter.Filter;
import br.com.hrs.core.repository.pagination.Pagination;

import java.util.List;
import java.util.Optional;

public interface Repository<E extends EntityKey<ID>, ID> {

    Optional<E> findById(ID id);
    
    E save(E entity);
    
    void update(E entity);
    
    List<E> findAll();

    List<E> findAll(Pagination pagination);

    List<E> findAll(Filter<E> filter);

    void deleteById(ID id);
    
    boolean existsById(ID id);

    long count();
}
