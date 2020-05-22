package br.com.hrs.service.repository.jpa;

import br.com.hrs.core.model.EntityKey;
import br.com.hrs.core.repository.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class SpringDataJpaRepositoryAbstractImpl<E extends EntityKey<ID>, ID> implements Repository<E, ID> {

    protected JpaRepository<E, ID> jpaRepository;

    public SpringDataJpaRepositoryAbstractImpl(JpaRepository<E, ID> jpaRepository) {
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
        Optional<E> E = this.jpaRepository.findById(id);
        boolean isPresent =  E.isPresent();
        logger.debug("Finding E id {}. Found: {}", id, isPresent);
        return isPresent ? E.get() : null;
    }

    @Override
    public List<E> findAll() {
        List<E> es = this.jpaRepository.findAll();
        logger.debug("Finding all es. Found: {}", es != null ? es.size() : 0);
        return es;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void update(E E) {
        save(E);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public E save(E E) {
        E = this.jpaRepository.saveAndFlush(E);
        logger.debug("Saving E. Saved: {}", E.getId());
        return E;
    }
}