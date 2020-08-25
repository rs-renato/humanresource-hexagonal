package br.com.hrs.persistence.repository.jpa.impl;


import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.CountryRepository;
import br.com.hrs.core.repository.filter.Filter;
import br.com.hrs.persistence.repository.jpa.JpaRepositoryAbstractImpl;
import br.com.hrs.persistence.repository.jpa.JpaRepositoryContainer;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Named
public class CountryJpaRepository extends JpaRepositoryAbstractImpl<Country, String> implements CountryRepository {

	private JpaRepositoryContainer.CountrySpringDataJpaRepositoryImpl jpaRepository;

	@Inject
	public CountryJpaRepository(JpaRepositoryContainer.CountrySpringDataJpaRepositoryImpl jpaRepository) {
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
	}

	public Optional<List<Country>> findByRegionId(Integer regionId) {
		logger.debug("findByRegionId({}})",regionId);

		if (Objects.isNull(regionId)) {
			Error.of("Region ID").when(FIELD.MANDATORY).trows();
		}

		return this.jpaRepository.findByRegionId(regionId);
	}

	@Override
	public List<Country> findAll(Filter<Country> filter) {

		//TODO build specifications.
		return null;
	}
}