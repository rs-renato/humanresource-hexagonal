package br.com.hrs.service.repository.jpa.queries;

import br.com.hrs.core.model.Country;

import java.util.List;

public interface CountryJpaRepositoryQueries {
    List<Country> findByRegionId(Integer regionId);
}
