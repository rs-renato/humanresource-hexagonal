/*package br.com.hrs.service.repository;

import br.com.hrs.core.model.Department;
import br.com.hrs.core.repository.DepartmentRepository;
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

@DisplayName("Database Service - Department")
@ContextConfiguration(classes = DatabaseConfiguration.class)
@ExtendWith(SpringExtension.class)
public class DepartmentRepositoryJdbcTest {

    private Integer DEPARTMENT_ID = 10;

    Logger logger = LogManager.getLogger(DepartmentRepositoryJdbcTest.class);

    @Inject
    private DepartmentRepository departmentRepository;

    @Test
    @DisplayName("Search for null department")
    public void test01() {
        Department department = departmentRepository.find(null);
        Assertions.assertNull(department, "Department should be null");
    }

    @Test
    @DisplayName("Search for inexistent department")
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
        departmentRepository.update(department);

        department = departmentRepository.find(DEPARTMENT_ID);

        Assertions.assertTrue(department.getName().contains("altered"), "Department should be altered");
    }

    @Test
    @DisplayName("Saves Department")
    public void test04() {
        Department department = new Department("NEW_DEPARTMENT", "new department", 1000f, 2000f);
        departmentRepository.save(department);

        department = departmentRepository.find("NEW_DEPARTMENT");

        Assertions.assertTrue(department.getTitle().contains("new"), "Department should be saved");
    }

    @Test
    @DisplayName("List Departments")
    public void test05() {
        Collection<Department> departments = departmentRepository.findAll();
        logger.info(departments);
        Assertions.assertNotNull(departments, "Departments should be listed");
    }

    @Test
    @DisplayName("Deletes Departments")
    public void test06() {
        Department department = departmentRepository.find("NEW_DEPARTMENT");
        logger.info(department);
        departmentRepository.delete(department.getId());
        boolean exists = departmentRepository.exists(department.getId());
        Assertions.assertFalse(exists, "Department should be deleted");
    }

    @Test
    @DisplayName("Department exists")
    public void test07() {
        boolean exists = departmentRepository.exists("HR_REP");
        Assertions.assertTrue(exists, "Department should be existent");
    }

    @Test
    @DisplayName("Department doesn't exists")
    public void test08() {
        boolean exists = departmentRepository.exists("NO_EXISTENT_DEPARTMENT");
        Assertions.assertFalse(exists, "Department should not be existent");
    }

    @Test
    @DisplayName("Search for department")
    public void test() {
        Department department = departmentRepository.find("HR_REP");
        logger.info(department);
        Assertions.assertNotNull(department, "Department should be null");
    }
}*/