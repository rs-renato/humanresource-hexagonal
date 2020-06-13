package br.com.hrs.core.repository.mock;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.EntityKey;
import br.com.hrs.core.repository.Repository;

import java.lang.reflect.ParameterizedType;
import java.util.*;

public abstract class MockRepository <E extends EntityKey<ID>, ID> implements Repository<E, ID>{

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

    public Optional<E> findById(ID id) {
        logger.debug("{} -> find({}})", REPOSITORY_NAME,id);
        
        if (Objects.isNull(id)) {
            Error.of(String.format("%s ID", ENTITY_NAME)).when(FIELD.MANDATORY).trows();
        }
        
        return Optional.ofNullable(this.database.get(id));
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
