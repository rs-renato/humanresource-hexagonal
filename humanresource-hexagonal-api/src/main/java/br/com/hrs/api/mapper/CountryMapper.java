package br.com.hrs.api.mapper;

import br.com.hrs.api.v1.country.resource.CountryResource;
import br.com.hrs.core.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper( CountryMapper.class );

    Country toModel(CountryResource countryResource);
    List<Country> toModelList(List<CountryResource> countryResource);

    CountryResource toResource(Country country);
    List<CountryResource> toResourceList(List<Country> country);

    default <T> T unwrap(Optional<T> optional) {
        return optional.orElse(null);
    }
}