package br.com.hrs.api.v1.mapper;

import br.com.hrs.api.v1.resource.DepartmentResource;
import br.com.hrs.core.model.Department;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper( DepartmentMapper.class );

    @Mappings({
            @Mapping(target="manager.id", source="departmentResource.managerId"),
            @Mapping(target="location.id", source="departmentResource.locationId")
    })
    Department toModel(DepartmentResource departmentResource);

    List<Department> toModelList(List<DepartmentResource> departmentResource);

    @InheritInverseConfiguration
    DepartmentResource toResource(Department department);

    List<DepartmentResource> toResourceList(List<Department> department);

    default <T> T unwrap(Optional<T> optional) {
        return optional.orElse(null);
    }
}