package br.com.hrs.service.repository.jpa.impl;

import br.com.hrs.core.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface CountrySpringDataJpaRepository extends JpaRepository<Country, String> {

	List<Country> findByRegionId(Integer regionId);
}