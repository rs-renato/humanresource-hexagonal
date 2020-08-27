package br.com.hrs.persistence.repository.jpa;

import br.com.hrs.core.model.*;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class JpaRepositoryContainer {

    @Named
    public interface CountrySpringDataJpaRepositoryImpl extends JpaRepositoryImplementation<Country, String> {
        Optional<List<Country>> findAllByRegionIdIn(Set<Integer> regionIds);
    }

    @Named
    public interface DepartmentSpringDataJpaRepositoryImpl extends JpaRepositoryImplementation<Department, Integer>{
       boolean existsDepartmentByName(String name);
    }

    @Named
    public interface EmployeeSpringDataJpaRepositoryImpl extends JpaRepositoryImplementation<Employee, Integer>{
        boolean existsByEmail(String email);
    }

    @Named
    public interface JobSpringDataJpaRepositoryImpl extends JpaRepositoryImplementation<Job, String> { }

    @Named
    public interface LocationSpringDataJpaRepositoryImpl extends JpaRepositoryImplementation<Location, Integer>{ }

    @Named
    public interface RegionSpringDataJpaRepositoryImpl extends JpaRepositoryImplementation<Region, Integer> {
        Optional<Region> findByName(String name);
    }
}
