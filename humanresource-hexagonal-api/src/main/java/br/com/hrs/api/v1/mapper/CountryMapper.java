package br.com.hrs.api.v1.mapper;

import br.com.hrs.api.v1.resource.CountryResource;
import br.com.hrs.core.model.Country;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper( CountryMapper.class );

    @Mappings({
            @Mapping(target="region.id", source="countryResource.regionId")
    })
    Country toModel(CountryResource countryResource);
    List<Country> toModelList(List<CountryResource> countryResource);

    @InheritInverseConfiguration
    CountryResource toResource(Country country);
    List<CountryResource> toResourceList(List<Country> country);

    default <T> T unwrap(Optional<T> optional) {
        return optional.orElse(null);
    }
}