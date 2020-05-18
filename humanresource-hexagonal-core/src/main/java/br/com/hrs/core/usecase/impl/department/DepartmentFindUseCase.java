package br.com.hrs.core.usecase.impl.department;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.core.usecase.FindUseCase;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DepartmentFindUseCase extends FindUseCase<Department, Integer> {

    @Inject
    public DepartmentFindUseCase(Repository<Department, Integer> repository) {
        super(repository);
    }
}
