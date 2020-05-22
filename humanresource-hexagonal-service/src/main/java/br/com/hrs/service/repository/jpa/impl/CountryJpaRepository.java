package br.com.hrs.service.repository.jpa.impl;


import br.com.hrs.core.model.Country;
import br.com.hrs.service.repository.jpa.SpringDataJpaRepositoryAbstractImpl;
import br.com.hrs.service.repository.jpa.adapter.CountrySpringDataJpaRepositoryImpl;
import br.com.hrs.service.repository.jpa.custom.CountryJpaRepositoryQueries;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class CountryJpaRepository extends SpringDataJpaRepositoryAbstractImpl<Country, String> implements CountryJpaRepositoryQueries {

	private CountrySpringDataJpaRepositoryImpl jpaRepository;

	@Inject
	public CountryJpaRepository(CountrySpringDataJpaRepositoryImpl jpaRepository) {
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
	}

	public List<Country> findByRegionId(Integer regionId) {
		List<Country> countries = this.jpaRepository.findByRegionId(regionId);
		logger.debug("Finding country by region id {}. Found: {}", regionId, countries != null ? countries.size() : 0);
		return countries;
	}
}