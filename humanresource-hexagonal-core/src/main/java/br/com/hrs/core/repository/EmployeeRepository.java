package br.com.hrs.core.repository;

import br.com.hrs.core.model.Employee;

public interface EmployeeRepository extends Repository<Employee, Integer> {

    boolean existsByEmail(String email);
}