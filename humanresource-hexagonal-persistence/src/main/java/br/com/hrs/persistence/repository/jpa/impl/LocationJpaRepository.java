package br.com.hrs.persistence.repository.jpa.impl;


import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.LocationRepository;
import br.com.hrs.persistence.repository.jpa.JpaRepositoryAbstractImpl;
import br.com.hrs.persistence.repository.jpa.JpaRepositoryContainer;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LocationJpaRepository extends JpaRepositoryAbstractImpl<Location, Integer> implements LocationRepository {

	private JpaRepositoryContainer.LocationSpringDataJpaRepositoryImpl jpaRepository;

	@Inject
	public LocationJpaRepository(JpaRepositoryContainer.LocationSpringDataJpaRepositoryImpl jpaRepository) {
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
	}
}