package br.com.hrs.service.repository.jpa.impl;

import br.com.hrs.core.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface CountrySpringDataJpaRepository extends JpaRepository<Country, String> {

	List<Country> findByRegionId(Integer regionId);
}