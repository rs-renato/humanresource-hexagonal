package br.com.hrs.core.usecase.country;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.usecase.CrudUseCase;

import java.util.List;

public interface CountryUseCase extends CrudUseCase<Country, String> {

    List<Country> findByRegionId(Integer regionId);
}