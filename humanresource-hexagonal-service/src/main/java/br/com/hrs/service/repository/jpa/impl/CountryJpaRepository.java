package br.com.hrs.service.repository.jpa.impl;


import br.com.hrs.core.exception.error.Error;
import br.com.hrs.core.exception.error.FIELD;
import br.com.hrs.core.model.Country;
import br.com.hrs.service.repository.jpa.JpaRepositoryAbstractImpl;
import br.com.hrs.service.repository.jpa.JpaRepositoryContainer;
import br.com.hrs.service.repository.jpa.queries.CountryJpaRepositoryQueries;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Objects;

@Named
public class CountryJpaRepository extends JpaRepositoryAbstractImpl<Country, String> implements CountryJpaRepositoryQueries {

	private JpaRepositoryContainer.CountrySpringDataJpaRepositoryImpl jpaRepository;

	@Inject
	public CountryJpaRepository(JpaRepositoryContainer.CountrySpringDataJpaRepositoryImpl jpaRepository) {
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
	}

	public List<Country> findByRegionId(Integer regionId) {
		logger.debug("{} -> findByRegionId({}})", REPOSITORY_NAME,regionId);

		if (Objects.isNull(regionId)) {
			Error.of("Region ID").when(FIELD.MANDATORY).trows();
		}

		List<Country> countries = this.jpaRepository.findByRegionId(regionId);
		logger.debug("Finding country by region id {}. Found: {}", regionId, countries != null ? countries.size() : 0);
		return countries;
	}
}