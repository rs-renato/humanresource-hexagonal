package br.com.hrs.api.v1.mapper;

import br.com.hrs.api.v1.resource.RegionResource;
import br.com.hrs.core.model.Region;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface RegionMapper {

    RegionMapper INSTANCE = Mappers.getMapper( RegionMapper.class );

    Region toModel(RegionResource regionResource);

    List<Region> toModelList(List<RegionResource> regionResource);

    @InheritInverseConfiguration
    RegionResource toResource(Region region);

    List<RegionResource> toResourceList(List<Region> region);

    default <T> T unwrap(Optional<T> optional) {
        return optional.orElse(null);
    }
}