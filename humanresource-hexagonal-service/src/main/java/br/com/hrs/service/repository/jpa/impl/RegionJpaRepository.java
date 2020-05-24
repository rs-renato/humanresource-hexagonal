package br.com.hrs.service.repository.jpa.impl;


import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Region;
import br.com.hrs.core.repository.RegionRepository;
import br.com.hrs.service.repository.jpa.JpaRepositoryAbstractImpl;
import br.com.hrs.service.repository.jpa.JpaRepositoryContainer;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;
import java.util.Optional;

@Named
public class RegionJpaRepository extends JpaRepositoryAbstractImpl<Region, Integer> implements RegionRepository {

	private JpaRepositoryContainer.RegionSpringDataJpaRepositoryImpl jpaRepository;

	@Inject
	public RegionJpaRepository(JpaRepositoryContainer.RegionSpringDataJpaRepositoryImpl jpaRepository) {
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
	}

	@Override
	public Optional<Region> findByName(String name) {
		logger.debug("{} -> findByName({}})", REPOSITORY_NAME,name);

		if (Objects.isNull(name)) {
			Error.of("Region Name").when(FIELD.MANDATORY).trows();
		}

		return this.jpaRepository.findByName(name);
	}
}