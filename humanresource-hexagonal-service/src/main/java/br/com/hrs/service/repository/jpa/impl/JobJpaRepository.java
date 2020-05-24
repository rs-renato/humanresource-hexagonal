package br.com.hrs.service.repository.jpa.impl;


import br.com.hrs.core.model.Job;
import br.com.hrs.service.repository.jpa.JpaRepositoryAbstractImpl;
import br.com.hrs.service.repository.jpa.JpaRepositoryContainer;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class JobJpaRepository extends JpaRepositoryAbstractImpl<Job, String> {

	private JpaRepositoryContainer.JobSpringDataJpaRepositoryImpl jpaRepository;

	@Inject
	public JobJpaRepository(JpaRepositoryContainer.JobSpringDataJpaRepositoryImpl jpaRepository) {
		super(jpaRepository);
		this.jpaRepository = jpaRepository;
	}
}