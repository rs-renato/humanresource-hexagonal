package br.com.hrs.persistence.repository.jpa.impl;


import br.com.hrs.core.model.Department;
import br.com.hrs.core.repository.DepartmentRepository;
import br.com.hrs.persistence.repository.jpa.JpaRepositoryAbstractImpl;
import br.com.hrs.persistence.repository.jpa.JpaRepositoryContainer;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DepartmentJpaRepository extends JpaRepositoryAbstractImpl<Department, Integer> implements DepartmentRepository {

	private JpaRepositoryContainer.DepartmentSpringDataJpaRepositoryImpl jpaRepository;

	@Inject
	public DepartmentJpaRepository(JpaRepositoryContainer.DepartmentSpringDataJpaRepositoryImpl jpaRepository) {
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
	}
}