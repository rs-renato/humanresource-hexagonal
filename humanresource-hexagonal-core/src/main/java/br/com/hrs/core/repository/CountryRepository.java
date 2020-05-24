package br.com.hrs.core.repository;

import br.com.hrs.core.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends Repository<Country, String> {

     Optional<List<Country>> findByRegionId(Integer regionId);
}