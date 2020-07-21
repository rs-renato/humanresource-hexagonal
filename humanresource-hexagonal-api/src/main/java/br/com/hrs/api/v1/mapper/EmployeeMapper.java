package br.com.hrs.api.v1.mapper;

import br.com.hrs.api.v1.resource.EmployeeResource;
import br.com.hrs.core.model.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper( EmployeeMapper.class );

    @Mappings({
            @Mapping(target="manager.id", source="employeeResource.managerId"),
            @Mapping(target="job.id", source="employeeResource.jobId"),
            @Mapping(target="department.id", source="employeeResource.departmentId"),
    })
    Employee toModel(EmployeeResource employeeResource);

    List<Employee> toModelList(List<EmployeeResource> employeeResource);

    @InheritInverseConfiguration
    EmployeeResource toResource(Employee employee);

    List<EmployeeResource> toResourceList(List<Employee> employee);

    default <T> T unwrap(Optional<T> optional) {
        return optional.orElse(null);
    }
}