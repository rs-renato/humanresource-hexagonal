package br.com.hrs.persistence.repository;

import br.com.hrs.core.exception.HrsMandatoryException;
import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Optional;

public abstract class DepartmentRepositoryTest {

    private Integer DEPARTMENT_ID = 12;
    private Integer MANAGER_ID = 100;
    private Integer LOCATION_ID = 15;

    protected abstract DepartmentRepository getDepartmentRepository();

    @Test
    @DisplayName("Finds for null Department")
    public void test01() {
        RuntimeException exception = Assertions.assertThrows(HrsMandatoryException.class, () -> {
            getDepartmentRepository().findById(null);
        });

        Assertions.assertTrue(exception.getMessage().matches("Department.*mandatory"), String.format("Department ID mandatory message wrong (%s)", exception.getMessage()));
    }

    @Test
    @DisplayName("Finds for inexistent Department")
    public void test02() {
        Optional<Department> departmentOpt = getDepartmentRepository().findById(-1);
        Assertions.assertFalse(departmentOpt.isPresent(), "Department should be null");
    }

    @Test
    @DisplayName("Updates Department")
    public void test03() {
        Optional<Department> department = getDepartmentRepository().findById(DEPARTMENT_ID);
        department.get().setName(department.get().getName() + " altered");
        department.get().setManager(new Employee.Builder().id(MANAGER_ID).build());
        getDepartmentRepository().update(department.get());

        department = getDepartmentRepository().findById(DEPARTMENT_ID);

        Assertions.assertTrue(department.get().getName().contains("altered"), "Department should be altered");
        Assertions.assertEquals(MANAGER_ID, department.get().getManager().getId(), "Department manager should be altered");
    }

    @Test
    @DisplayName("Saves Department")
    public void test04() {
        Department department = new Department("new department", new Employee.Builder().id(MANAGER_ID).build(), new Location.Builder().id(LOCATION_ID).build());
        Department departmentSaved = getDepartmentRepository().save(department);
        Assertions.assertNotNull(departmentSaved, String.format("Department saved should not be null"));
        Optional<Department> departmentOpt = getDepartmentRepository().findById(departmentSaved.getId());
        Assertions.assertEquals(departmentSaved, departmentOpt.get(), String.format("Department should be equals"));
    }

    @Test
    @DisplayName("Finds all Departments")
    public void test05() {
        Collection<Department> departments = getDepartmentRepository().findAll();
        Assertions.assertNotNull(departments, "Departments should be listed");
        Assertions.assertTrue(departments.size() >= 27, "Departments should be listed at all");
    }

    @Test
    @DisplayName("Finds for Department")
    public void test06() {
        Optional<Department> departmentOpt = getDepartmentRepository().findById(DEPARTMENT_ID);
        Assertions.assertTrue(departmentOpt.isPresent(), "Department should not be null");
    }

    @Test
    @DisplayName("Department exists")
    public void test07() {
        boolean exists = getDepartmentRepository().existsById(DEPARTMENT_ID);
        Assertions.assertTrue(exists, "Department should be existent");
    }

    @Test
    @DisplayName("Department doesn't exists")
    public void test08() {
        boolean exists = getDepartmentRepository().existsById(-1);
        Assertions.assertFalse(exists, "Department should not be existent");
    }

    @Test
    @DisplayName("Deletes Department")
    public void test09() {
        getDepartmentRepository().deleteById(DEPARTMENT_ID);
        Optional<Department> departmentOpt = getDepartmentRepository().findById(DEPARTMENT_ID);
        Assertions.assertFalse(departmentOpt.isPresent(), "Department still existing");
    }
}