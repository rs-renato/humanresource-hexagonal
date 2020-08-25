package br.com.hrs.core.repository.mock;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.EntityKey;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.repository.filter.Filter;
import br.com.hrs.core.repository.pagination.Pagination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

public abstract class MockRepository <E extends EntityKey<ID>, ID> implements Repository<E, ID>{

    protected static Logger logger = LogManager.getLogger(MockRepository.class);

    protected final Map<ID, E> database = new HashMap<>();
    protected final String REPOSITORY_NAME = this.getClass().getSimpleName();
    protected final String ENTITY_NAME  = getGenericName();

    public MockRepository() {

        loadMockDatabase(buildCollection());
    }

    abstract Collection<E> buildCollection();

    private void loadMockDatabase(Collection<E> collection){
        collection.forEach(entity -> database.put(entity.getId(), entity));
    }

    @Override
    public long count() {
        logger.debug("{} -> count()", REPOSITORY_NAME);
        return this.database.size();
    }

    public Optional<E> findById(ID id) {
        logger.debug("{} -> find({}})", REPOSITORY_NAME,id);
        
        if (Objects.isNull(id)) {
            Error.of(String.format("%s ID", ENTITY_NAME)).when(FIELD.MANDATORY).trows();
        }

        E entity = this.database.get(id);
        return entity != null ? Optional.of(entity) : Optional.empty();
    }

    public E save(E entity) {
        logger.debug("{} ->  save({}})", REPOSITORY_NAME, entity);

        if (Objects.isNull(entity)) {
            Error.of(ENTITY_NAME).when(FIELD.MANDATORY).trows();
        }

        this.database.put(entity.getId(), entity);
        return entity;
    }

    public void update(E entity) {
        logger.debug("{} ->  update({}})", REPOSITORY_NAME, entity);

        if (Objects.isNull(entity)) {
            Error.of(ENTITY_NAME).when(FIELD.MANDATORY).trows();
        }
        
        this.database.put(entity.getId(), entity);
    }

    public List<E> findAll() {
        logger.debug("{} -> findAll()", REPOSITORY_NAME);
        return new ArrayList(this.database.values());
    }

    public List<E> findAll(Pagination pagination) {
        logger.debug("{} -> findAll()", REPOSITORY_NAME);
        return new PageableArrayList<E>(findAll(), pagination.getPage(), pagination.getSize()).getListForPage();
    }

    @Override
    public List<E> findAll(Filter<E> filter) {
        return findAll().stream()
                .filter(filter.filterToPredicate())
                .collect(Collectors.toList());
    }

    public void deleteById(ID id) {
        logger.debug("{} ->  delete({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
            Error.of(String.format("%s ID", ENTITY_NAME)).when(FIELD.MANDATORY).trows();
        }
        
        this.database.remove(id);
    }

    public boolean existsById(ID id) {
        logger.debug("{} ->  exists({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
            Error.of(String.format("%s ID", ENTITY_NAME)).when(FIELD.MANDATORY).trows();
        }
        
        return this.database.containsKey(id);
    }

    private String getGenericName(){
        return ((Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
    }
}
