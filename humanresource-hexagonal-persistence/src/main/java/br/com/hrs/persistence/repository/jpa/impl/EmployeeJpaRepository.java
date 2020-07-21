package br.com.hrs.persistence.repository.jpa.impl;


import br.com.hrs.core.model.Employee;
import br.com.hrs.core.repository.EmployeeRepository;
import br.com.hrs.persistence.repository.jpa.JpaRepositoryAbstractImpl;
import br.com.hrs.persistence.repository.jpa.JpaRepositoryContainer;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EmployeeJpaRepository extends JpaRepositoryAbstractImpl<Employee, Integer> implements EmployeeRepository {

	private JpaRepositoryContainer.EmployeeSpringDataJpaRepositoryImpl jpaRepository;

	@Inject
	public EmployeeJpaRepository(JpaRepositoryContainer.EmployeeSpringDataJpaRepositoryImpl jpaRepository) {
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
	}

	@Override
	public boolean existsByEmail(String email) {
		return this.jpaRepository.existsByEmail(email);
	}
}