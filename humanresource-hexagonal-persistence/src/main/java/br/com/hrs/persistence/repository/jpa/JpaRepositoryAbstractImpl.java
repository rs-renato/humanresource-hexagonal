package br.com.hrs.persistence.repository.jpa;

import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.EntityKey;
import br.com.hrs.core.repository.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class JpaRepositoryAbstractImpl<E extends EntityKey<ID>, ID> implements Repository<E, ID> {

    protected JpaRepository<E, ID> jpaRepository;
    
    protected final String REPOSITORY_NAME = this.getClass().getSimpleName();
    protected final String ENTITY_NAME  = getGenericName();

    protected JpaRepositoryAbstractImpl(JpaRepository<E, ID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<E> findById(ID id) {
        logger.debug("{} -> find({}})", REPOSITORY_NAME,id);

        if (Objects.isNull(id)) {
            Error.of(String.format("%s ID", ENTITY_NAME)).when(FIELD.MANDATORY).trows();
        }
        
        Optional<E> entity = this.jpaRepository.findById(id);
        boolean isPresent =  entity.isPresent();
        logger.debug("{} id {} found: {}",ENTITY_NAME, id, isPresent);
        return isPresent ? entity : Optional.empty();
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public E save(E entity) {
        logger.debug("{} ->  save({}})", REPOSITORY_NAME, entity);

        if (Objects.isNull(entity)) {
            Error.of(ENTITY_NAME).when(FIELD.MANDATORY).trows();
        }
        
        entity = this.jpaRepository.saveAndFlush(entity);
        logger.debug("{}. Saved: {}",ENTITY_NAME , entity.getId());
        return entity;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void update(E entity) {
        logger.debug("{} ->  update({}})", REPOSITORY_NAME, entity);

        if (Objects.isNull(entity)) {
            Error.of(ENTITY_NAME).when(FIELD.MANDATORY).trows();
        }
        save(entity);
    }
    
    @Override
    public List<E> findAll() {
        logger.debug("{} -> findAll()", REPOSITORY_NAME);
        List<E> entities = this.jpaRepository.findAll();
        logger.debug("Finding all entities. Found: {}", entities != null ? entities.size() : 0);
        return entities;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void deleteById(ID id) {
        logger.debug("{} ->  deleteById({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
            Error.of(String.format("%s ID", ENTITY_NAME)).when(FIELD.MANDATORY).trows();
        }
        
        this.jpaRepository.deleteById(id);
        this.jpaRepository.flush();
        logger.debug("Deleted {} by id {}.", ENTITY_NAME, id);
    }

    @Override
    public boolean existsById(ID id) {
        logger.debug("{} ->  existsById({}})", REPOSITORY_NAME, id);

        if (Objects.isNull(id)) {
            Error.of(String.format("%s ID", ENTITY_NAME)).when(FIELD.MANDATORY).trows();
        }
        
        boolean exists = this.jpaRepository.existsById(id);
        logger.debug("Verifying existence of {} id {}. Exists: {}", ENTITY_NAME, id, exists);
        return exists;
    }

    private String getGenericName(){
        return ((Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
    }
}