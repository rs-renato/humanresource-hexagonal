package br.com.hrs.persistence.repository.jpa.impl;


import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.CountryRepository;
import br.com.hrs.persistence.repository.jpa.JpaRepositoryAbstractImpl;
import br.com.hrs.persistence.repository.jpa.JpaRepositoryContainer;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Named
public class CountryJpaRepository extends JpaRepositoryAbstractImpl<Country, String> implements CountryRepository {

	private JpaRepositoryContainer.CountrySpringDataJpaRepositoryImpl jpaRepository;

	@Inject
	public CountryJpaRepository(JpaRepositoryContainer.CountrySpringDataJpaRepositoryImpl jpaRepository) {
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
	}

	@Override
	public Optional<List<Country>> findAllByRegionIdIn(Set<Integer> regionIds) {
		logger.debug("findByRegionId({}})",regionIds);

		if (Objects.isNull(regionIds)) {
			Error.of("Region ID").when(FIELD.MANDATORY).trows();
		}

		return this.jpaRepository.findAllByRegionIdIn(regionIds);
	}
}