package br.com.hrs.api.v1.mapper;

import br.com.hrs.api.v1.resource.LocationResource;
import br.com.hrs.core.model.Location;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationMapper INSTANCE = Mappers.getMapper( LocationMapper.class );

    @Mappings({
            @Mapping(target="country.id", source="locationResource.countryId"),
    })
    Location toModel(LocationResource locationResource);

    List<Location> toModelList(List<LocationResource> locationResource);

    @InheritInverseConfiguration
    LocationResource toResource(Location location);

    List<LocationResource> toResourceList(List<Location> location);

    default <T> T unwrap(Optional<T> optional) {
        return optional.orElse(null);
    }
}