package br.com.hrs.core.usecase.department;

import br.com.hrs.core.HrsBuildConfiguration;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.pagination.Pagination;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

@ContextConfiguration(classes = HrsBuildConfiguration.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Department Crud Use Case")
public class DepartmentCrudUseCaseTest {

    @Inject
    private DepartmentUseCase departmentCrudUseCase;

    private static Department department;

    @BeforeAll
    public static void setUp(){
        department =  new Department.Builder()
                .id(1)
                .name("Administration")
                .manager(new Employee.Builder().id(101).build())
                .location(new Location.Builder().id(1500).build())
                .build();
    }

    @Test
    @DisplayName("Exists an Departments")
    public void test01() {
        boolean exists = departmentCrudUseCase.existsById(department.getId());
        Assertions.assertTrue(exists, String.format("Department should exist"));
    }

    @Test
    @DisplayName("Saves an Department")
    public void test02() {
        Department departmentSaved = departmentCrudUseCase.save(department);
        Assertions.assertNotNull(departmentSaved, String.format("Department saved should not be null"));
        Optional<Department> departmentOpt = departmentCrudUseCase.findById(departmentSaved.getId());
        Assertions.assertEquals(departmentSaved, departmentOpt.get(), String.format("Department should be equals"));
    }

    @Test
    @DisplayName("Finds an Department")
    public void test03() {
        Optional<Department> departmentOpt = departmentCrudUseCase.findById(department.getId());
        Assertions.assertTrue(departmentOpt.isPresent(), String.format("Department should not be null"));
    }

    @Test
    @DisplayName("Finds all Departments")
    public void test04() {
        Collection<Department> departments = departmentCrudUseCase.findAll();
        Assertions.assertNotNull(departments, String.format("Departments should not be null"));
        Assertions.assertEquals(2, departments.size(), String.format("Departments size doesn't match"));
    }

    @Test
    @DisplayName("Updates an Department")
    public void test05() {

        department.setName(department.getName() + "updated");

        departmentCrudUseCase.update(department);

        Optional<Department> departmentOpt = departmentCrudUseCase.findById(department.getId());

        Assertions.assertTrue(departmentOpt.isPresent(), String.format("Department should not be null"));
        Assertions.assertEquals(departmentOpt.get().getName(), department.getName(), String.format("Department name should be updated"));
    }

    @Test
    @DisplayName("Deletes an Department")
    public void test06() {

        departmentCrudUseCase.deleteById(department.getId());

        Optional<Department> departmentOpt = departmentCrudUseCase.findById(department.getId());

        Assertions.assertFalse(departmentOpt.isPresent(), String.format("Department should be null"));
    }

    @Test
    @DisplayName("Finds all Departments Paginated")
    public void test07() {

        long count = departmentCrudUseCase.count();

        int pageSize = 1;

        for (int i = 1; i < count; i++) {

            int page = i * pageSize;

            Pagination pagination = new  Pagination(page, pageSize);

            Collection<Department> departments = departmentCrudUseCase.findAll(pagination);
            Assertions.assertNotNull(departments, "Departments should be listed");
            Assertions.assertTrue(departments.size() <= pageSize, "Departments should be listed at all");

            new LinkedList<Department>(departmentCrudUseCase.findAll())
                    .subList(page, page + pageSize)
                    .forEach(c -> c.equals(departments.iterator().next()));
        }
    }
}
