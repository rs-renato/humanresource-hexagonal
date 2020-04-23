package br.com.hrs.service.repository;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.model.Employee;
import br.com.hrs.core.model.Location;
import br.com.hrs.core.repository.Repository;
import br.com.hrs.service.DatabaseConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.util.Collection;

@DisplayName("Repository - Department")
@ContextConfiguration(classes = DatabaseConfiguration.class)
@ExtendWith(SpringExtension.class)
public class DepartmentRepositoryJdbcTest {

    private Integer DEPARTMENT_ID = 12;
    private Integer MANAGER_ID = 100;
    private Integer LOCATION_ID = 15;

    Logger logger = LogManager.getLogger(DepartmentRepositoryJdbcTest.class);

    @Inject
    private Repository<Department, Integer> departmentRepository;

    @Test
    @DisplayName("Finds for null Department")
    public void test01() {
        Department department = departmentRepository.find(null);
        Assertions.assertNull(department, "Department should be null");
    }

    @Test
    @DisplayName("Finds for inexistent Department")
    public void test02() {
        Department department = departmentRepository.find(-1);
        logger.info(department);
        Assertions.assertNull(department, "Department should be null");
    }

    @Test
    @DisplayName("Updates Department")
    public void test03() {
        Department department = departmentRepository.find(DEPARTMENT_ID);
        logger.info(department);
        department.setName(department.getName() + " altered");
        department.setManager(new Employee.Builder().id(MANAGER_ID).build());
        boolean updated = departmentRepository.update(department);

        department = departmentRepository.find(DEPARTMENT_ID);

        Assertions.assertTrue(updated, "Department should be updated");
        Assertions.assertTrue(department.getName().contains("altered"), "Department should be altered");
        Assertions.assertTrue(MANAGER_ID == department.getManager().getId(), "Department manager should be altered");
    }

    @Test
    @DisplayName("Saves Department")
    public void test04() {
        Department department = new Department("new department", new Employee.Builder().id(MANAGER_ID).build(), new Location.Builder().id(LOCATION_ID).build());
        Integer savedId = departmentRepository.save(department);

        department = departmentRepository.find(savedId);

        Assertions.assertNotNull(savedId, "Department should be saved and return");
        Assertions.assertNotNull(department, "Department should be saved");
        Assertions.assertTrue(savedId.equals(department.getId()), "Department saved needs to match saved id");
    }

    @Test
    @DisplayName("Finds all Departments")
    public void test05() {
        Collection<Department> departments = departmentRepository.findAll();
        logger.info(departments);
        Assertions.assertNotNull(departments, "Departments should be listed");
        Assertions.assertTrue(departments.size() >= 27, "Departments should be listed at all");
    }

    @Test
    @DisplayName("Finds for Department")
    public void test06() {
        Department department = departmentRepository.find(DEPARTMENT_ID);
        logger.info(department);
        Assertions.assertNotNull(department, "Department should not be null");
    }

    @Test
    @DisplayName("Department exists")
    public void test07() {
        boolean exists = departmentRepository.exists(DEPARTMENT_ID);
        Assertions.assertTrue(exists, "Department should be existent");
    }

    @Test
    @DisplayName("Department doesn't exists")
    public void test08() {
        boolean exists = departmentRepository.exists(-1);
        Assertions.assertFalse(exists, "Department should not be existent");
    }

    @Test
    @DisplayName("Deletes Department")
    public void test09() {
        boolean deleted = departmentRepository.delete(DEPARTMENT_ID);
        Department department = departmentRepository.find(DEPARTMENT_ID);
        Assertions.assertTrue(deleted, "Department should be deleted");
        Assertions.assertNull(department, "Department still existing");
    }

    @Test
    @DisplayName("Deletes inexistend Department")
    public void test10() {
        boolean deleted = departmentRepository.delete(-1);
        Assertions.assertFalse(deleted, "Department should not be deleted");
    }
}