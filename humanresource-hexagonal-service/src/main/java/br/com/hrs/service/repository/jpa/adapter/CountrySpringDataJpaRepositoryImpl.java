package br.com.hrs.service.repository.jpa.adapter;


import br.com.hrs.core.model.Country;
import br.com.hrs.service.repository.jpa.custom.CountryJpaRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.inject.Named;

@Named
public interface CountrySpringDataJpaRepositoryImpl extends JpaRepository<Country, String>, CountryJpaRepositoryQueries {

}
