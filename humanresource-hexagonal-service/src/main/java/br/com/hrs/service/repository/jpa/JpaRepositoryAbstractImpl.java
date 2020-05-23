package br.com.hrs.service.repository.jpa;

import br.com.hrs.core.model.EntityKey;
import br.com.hrs.core.repository.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class JpaRepositoryAbstractImpl<E extends EntityKey<ID>, ID> implements Repository<E, ID> {

    protected JpaRepository<E, ID> jpaRepository;

    protected JpaRepositoryAbstractImpl(JpaRepository<E, ID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void delete(ID id) {
        this.jpaRepository.deleteById(id);
        this.jpaRepository.flush();
        logger.debug("Deleted E by id {}.", id);
    }

    @Override
    public boolean exists(ID id) {
        boolean exists = this.jpaRepository.existsById(id);
        logger.debug("Verifying existence of E id {}. Exists: {}", id, exists);
        return exists;
    }

    @Override
    public E find(ID id) {
        Optional<E> entity = this.jpaRepository.findById(id);
        boolean isPresent =  entity.isPresent();
        logger.debug("Finding entity id {}. Found: {}", id, isPresent);
        return isPresent ? entity.get() : null;
    }

    @Override
    public List<E> findAll() {
        List<E> entities = this.jpaRepository.findAll();
        logger.debug("Finding all entities. Found: {}", entities != null ? entities.size() : 0);
        return entities;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void update(E entity) {
        save(entity);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public E save(E entity) {
        entity = this.jpaRepository.saveAndFlush(entity);
        logger.debug("Saving entity. Saved: {}", entity.getId());
        return entity;
    }
}