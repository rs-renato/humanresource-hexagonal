package br.com.hrs.persistence.repository.jpa;

import br.com.hrs.core.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;

public class JpaRepositoryContainer {

    @Named
    public interface CountrySpringDataJpaRepositoryImpl extends JpaRepository<Country, String> {
        Optional<List<Country>> findByRegionId(Integer regionId);
    }

    @Named
    public interface DepartmentSpringDataJpaRepositoryImpl extends JpaRepository<Department, Integer>{
       boolean existsDepartmentByName(String name);
    }

    @Named
    public interface EmployeeSpringDataJpaRepositoryImpl extends JpaRepository<Employee, Integer>{ }

    @Named
    public interface JobSpringDataJpaRepositoryImpl extends JpaRepository<Job, String> { }

    @Named
    public interface LocationSpringDataJpaRepositoryImpl extends JpaRepository<Location, Integer>{ }

    @Named
    public interface RegionSpringDataJpaRepositoryImpl extends JpaRepository<Region, Integer> {
        Optional<Region> findByName(String name);
    }
}
