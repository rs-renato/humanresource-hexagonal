package br.com.hrs.service.repository.jpa.impl;


import br.com.hrs.core.model.Country;
import br.com.hrs.core.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@Named
public class CountryJpaRepository implements Repository<Country, String> {

	private CountrySpringDataJpaRepository jpaRepository;

	@Inject
	public CountryJpaRepository(CountrySpringDataJpaRepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delete(String id) {
		this.jpaRepository.deleteById(id);
		this.jpaRepository.flush();
		logger.debug("Deleted country by id {}.", id);
	}

	@Override
	public boolean exists(String id) {
		boolean exists = this.jpaRepository.existsById(id);
		logger.debug("Verifying existence of country id {}. Exists: {}", id, exists);
		return exists;
	}

	@Override
	public Country find(String id) {
		Optional<Country> country = this.jpaRepository.findById(id);
		boolean isPresent =  country.isPresent();
		logger.debug("Finding country id {}. Found: {}", id, isPresent);
		return isPresent ? country.get() : null;
	}

	public List<Country> findByRegionId(Integer regionId) {
		List<Country> countries = this.jpaRepository.findByRegionId(regionId);
		logger.debug("Finding country by region id {}. Found: {}", regionId, countries != null ? countries.size() : 0);
		return countries;
	}
	
	@Override
	public List<Country> findAll() {
		List<Country> countries = this.jpaRepository.findAll();
		logger.debug("Finding all countries. Found: {}", countries != null ? countries.size() : 0);
		return countries;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void update(Country country) {
		save(country);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Country save(Country country) {
		country = this.jpaRepository.saveAndFlush(country);
		logger.debug("Saving country. Saved: {}", country.getId());
		return country;
	}
}