package br.com.hrs.service.repository.jpa;

import br.com.hrs.core.model.Country;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Job;
import br.com.hrs.service.repository.jpa.queries.CountryJpaRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.inject.Named;

public class JpaRepositoryContainer {

    @Named
    public interface CountrySpringDataJpaRepositoryImpl extends JpaRepository<Country, String>, CountryJpaRepositoryQueries { }

    @Named
    public interface DepartmentSpringDataJpaRepositoryImpl extends JpaRepository<Department, Integer>{ }

    @Named
    public interface JobSpringDataJpaRepositoryImpl extends JpaRepository<Job, String> { }
}
