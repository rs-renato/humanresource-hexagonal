package br.com.hrs.core.repository;

import br.com.hrs.core.model.Country;

import java.util.List;

public interface CountryRepository extends Repository<Country, String> {

     List<Country> findByRegionId(Integer regionId);
}