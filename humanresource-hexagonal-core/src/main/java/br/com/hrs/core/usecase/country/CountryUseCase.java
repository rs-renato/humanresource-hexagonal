package br.com.hrs.core.usecase.country;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.usecase.CrudUseCase;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CountryUseCase extends CrudUseCase<Country, String> {

    Optional<List<Country>> findAllByRegionIdIn(Set<Integer> regionId);
}